package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.dto.PedidoDto;
import br.com.caique.desafiozup.form.PedidoAtualizacaoForm;
import br.com.caique.desafiozup.form.PedidoForm;
import br.com.caique.desafiozup.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/pedido")
public class PedidosController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar() {
        return ResponseEntity.ok(pedidoService.listar(0, 10));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> cadastrar(@RequestBody PedidoForm form) {
        return ResponseEntity.ok(pedidoService.cadastrar(form));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        return pedidoService.remover(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id, @RequestBody PedidoAtualizacaoForm form) {
        return pedidoService.atualizar(id, form);
    }
}
