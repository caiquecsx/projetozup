package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.dto.PedidoDto;
import br.com.caique.desafiozup.form.PedidoForm;
import br.com.caique.desafiozup.model.Pedido;
import br.com.caique.desafiozup.repository.PedidoRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidosController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar() {
        Pageable pageable = PageRequest.of(0, 10);
        return ResponseEntity.ok(new PedidoDto().converter(pedidoRepository.findAll(pageable)));
    }

    @PostMapping
    @Transactional
    public PedidoForm cadastrar(@RequestBody PedidoForm form) {
        Pedido pedido = form.converter(produtoRepository);
        pedidoRepository.save(pedido);
        //TODO retornar responseEntity de um DTO
        return form;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if(pedidoOptional.isPresent()) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id) {
        //TODO: PedidoAtualizacaoForm form
    }
}
