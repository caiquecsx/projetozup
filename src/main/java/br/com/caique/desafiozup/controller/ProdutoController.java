package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.dto.ProdutoDto;
import br.com.caique.desafiozup.form.ProdutoAtualizacaoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.service.ProdutoService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

//    @Autowired
//    Tracer tracer;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @Cacheable(value = "listaDeProdutos")
    public ResponseEntity<Page<ProdutoDto>> listar(@RequestParam(required = false, defaultValue = "0") int pagina,
                                                   @RequestParam(required = false, defaultValue = "10") int quantidade) {
//        Span span = tracer.buildSpan("generate-name").start();

        return ResponseEntity.ok(produtoService.listar(pagina, quantidade));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoForm produtoForm) {
        Optional<ProdutoDto> produtoDto = produtoService.cadastrar(produtoForm);
        if(produtoDto.isPresent()){
            return ResponseEntity.ok(produtoDto.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        if(produtoService.remover(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoAtualizacaoForm form) {
        Optional<ProdutoDto> produtoDto = produtoService.atualizar(id, form);
        if(produtoDto.isPresent()){
            return ResponseEntity.ok(produtoDto.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhes(@PathVariable Long id) {
        Optional<ProdutoDto> produtoDtoOptional = produtoService.detalhar(id);
        if(produtoDtoOptional.isPresent()){
            return ResponseEntity.ok(produtoDtoOptional);
        }
        return ResponseEntity.notFound().build();
    }

}
