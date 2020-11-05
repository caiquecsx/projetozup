package br.com.caique.desafiozup.service;

import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.form.ProdutoAtualizacaoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.FabricanteRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FabricanteRepository fabricanteRepository;

    public Page<ProdutoDto> listar(int pagina, int quantidade) {
        Pageable pageable = PageRequest.of(pagina, quantidade);
        return new ProdutoDto().converter(produtoRepository.findAll(pageable));
    }

    public ProdutoDto cadastrar(ProdutoForm produtoForm) {
        Produto produto = produtoForm.converter(fabricanteRepository);
        produtoRepository.save(produto);
        return new ProdutoDto(produto);
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
