package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.dto.PedidoDto;
import br.com.caique.desafiozup.form.PedidoAtualizacaoForm;
import br.com.caique.desafiozup.form.PedidoForm;
import br.com.caique.desafiozup.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidosController {

    private final PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(@RequestParam(required = false, defaultValue = "0") int pagina,
                                                  @RequestParam(required = false, defaultValue = "10") int quantidade) {
        return ResponseEntity.ok(pedidoService.listar(pagina, quantidade));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> cadastrar(@RequestBody PedidoForm form) {
        return ResponseEntity.ok(pedidoService.cadastrar(form));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if(pedidoService.remover(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id, @RequestBody PedidoAtualizacaoForm form) {
        Optional<PedidoDto> pedidoDtoOptional = pedidoService.atualizar(id, form);
        if(pedidoDtoOptional.isPresent()){
            return ResponseEntity.ok(pedidoDtoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> detalhes(@PathVariable Long id) {
        Optional<PedidoDto> pedidoDtoOptional = pedidoService.detalhar(id);
        if(pedidoDtoOptional.isPresent()){
            return ResponseEntity.ok(pedidoDtoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
