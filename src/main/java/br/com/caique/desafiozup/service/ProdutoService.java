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

    public Optional<ProdutoDto> cadastrar(ProdutoForm produtoForm) {
        Produto produto = null;
        try {
            produto = produtoForm.converter(fabricanteRepository);
            produtoRepository.save(produto);
            return Optional.of(new ProdutoDto(produto));
        } catch (FabricanteInvalidoException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean remover(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ProdutoDto> atualizar(Long id, ProdutoAtualizacaoForm produtoAtualizacaoForm) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            Produto produto = produtoAtualizacaoForm.atualizar(id, produtoRepository);
            return Optional.of(new ProdutoDto(produto));
        }
        return Optional.empty();
    }

}
