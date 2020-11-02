package br.com.caique.desafiozup.form;

import br.com.caique.desafiozup.helper.PedidoHelper;
import br.com.caique.desafiozup.model.Pedido;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.ProdutoRepository;

import java.util.List;

public class PedidoForm {

    private String cliente;
    private String telefone;
    private List<Long> produtos;
    private Double desconto;

    public Pedido converter(ProdutoRepository produtoRepository) {
        List<Produto> produtos;
        produtos = PedidoHelper.AtualizarListaDeProdutos(produtoRepository, this.produtos);

        Pedido pedido = new Pedido(this.cliente, this.telefone, produtos, this.desconto);
        return pedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
