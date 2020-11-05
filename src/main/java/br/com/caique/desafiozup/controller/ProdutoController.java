package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.form.ProdutoAtualizacaoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listar(@RequestParam int pagina, @RequestParam int quantidade) {
        return ResponseEntity.ok(produtoService.listar(pagina, quantidade));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm produtoForm) {
        return ResponseEntity.ok(produtoService.cadastrar(produtoForm));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        //TODO Validação do ID
        return produtoService.remover(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoAtualizacaoForm form) {
        return produtoService.atualizar(id, form);
    }

}
