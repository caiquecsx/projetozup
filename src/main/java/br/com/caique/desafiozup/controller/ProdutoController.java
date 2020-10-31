package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.FabricanteRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FabricanteRepository fabricanteRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> lista(@RequestParam int pagina,
                                                  @RequestParam int quantidade) {
        Pageable pageable = PageRequest.of(pagina, quantidade);
        return ResponseEntity.ok(new ProdutoDto().converter(produtoRepository.findAll(pageable)));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm produtoForm) {
        Produto produto = produtoForm.converter(fabricanteRepository);
        produtoRepository.save(produto);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }



}
