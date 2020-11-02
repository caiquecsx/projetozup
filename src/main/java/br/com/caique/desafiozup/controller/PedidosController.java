package br.com.caique.desafiozup.controller;

import br.com.caique.desafiozup.form.PedidoForm;
import br.com.caique.desafiozup.form.ProdutoForm;
import br.com.caique.desafiozup.model.Pedido;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.PedidoRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidosController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> listar() {
        List<Pedido> pedido = pedidoRepository.findAll();
        return pedido;
    }

    @PostMapping
    @Transactional
    public PedidoForm cadastrar(@RequestBody PedidoForm form) {
        Pedido pedido = form.converter(produtoRepository);
        pedidoRepository.save(pedido);
        return form;
    }

    public void remover() {

    }

    public void atualizar() {

    }
}
