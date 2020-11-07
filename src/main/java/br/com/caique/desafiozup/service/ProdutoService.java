package br.com.caique.desafiozup.service;

import br.com.caique.desafiozup.DesafiozupApplication;
import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.exception.FabricanteInvalidoException;
import br.com.caique.desafiozup.form.ProdutoAtualizacaoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.FabricanteRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FabricanteRepository fabricanteRepository;

    private static Logger logger = LoggerFactory.getLogger(DesafiozupApplication.class);

    public ProdutoService(ProdutoRepository produtoRepository, FabricanteRepository fabricanteRepository) {
        this.produtoRepository = produtoRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    public Page<ProdutoDto> listar(int pagina, int quantidade) {
        Pageable pageable = PageRequest.of(pagina, quantidade);
        return new ProdutoDto().converter(produtoRepository.findAll(pageable));
    }

    public ResponseEntity<?> cadastrar(ProdutoForm produtoForm) {
        Produto produto = null;
        try {
            produto = produtoForm.converter(fabricanteRepository);
            produtoRepository.save(produto);
            return ResponseEntity.ok(new ProdutoDto(produto));
        } catch (FabricanteInvalidoException e) {
            logger.error(e.getMessage());

            //TODO padronizar retorno de erro
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    public ResponseEntity<?> remover(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> atualizar(Long id, ProdutoAtualizacaoForm produtoAtualizacaoForm) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            Produto produto = produtoAtualizacaoForm.atualizar(id, produtoRepository);
            return ResponseEntity.ok(new ProdutoDto(produto));
        }
        return ResponseEntity.notFound().build();
    }

}
