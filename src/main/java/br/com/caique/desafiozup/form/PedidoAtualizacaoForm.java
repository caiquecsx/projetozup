package br.com.caique.desafiozup.form;

import br.com.caique.desafiozup.helper.PedidoHelper;
import br.com.caique.desafiozup.model.Pedido;
import br.com.caique.desafiozup.repository.PedidoRepository;
import br.com.caique.desafiozup.repository.ProdutoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PedidoAtualizacaoForm {

    @NotNull
    @NotEmpty
    private List<Long> produtos;
    @NotNull
    private Double desconto;

    public Pedido atualizar(Long id, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        Pedido pedido = pedidoRepository.getOne(id);
        pedido.setDesconto(this.desconto);
        pedido.setProdutos(PedidoHelper.AtualizarListaDeProdutos(produtoRepository, produtos));

        return pedido;
    }

    public List<Long> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Long> produtos) {
        this.produtos = produtos;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}
