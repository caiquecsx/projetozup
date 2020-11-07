package br.com.caique.desafiozup.dto;

import br.com.caique.desafiozup.model.Pedido;
import br.com.caique.desafiozup.model.Produto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDto {

    private Long id;
    private String cliente;
    private String telefone;
    private List<Produto> produtos;
    private Double desconto;
    private BigDecimal valorTotal;

    public PedidoDto() {}

    public PedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = pedido.getCliente();
        this.telefone = pedido.getTelefone();
        this.produtos = pedido.getProdutos();
        this.desconto = pedido.getDesconto();

        calculaValorTotalComDesconto(produtos);
    }

    public Page<PedidoDto> converter(Page<Pedido> pedidos) {
        return pedidos.map(PedidoDto::new);
    }

    private void calculaValorTotalComDesconto(List<Produto> produtos) {
        this.valorTotal = BigDecimal.ZERO;
        produtos.forEach(produto -> {
            this.valorTotal = this.valorTotal.add(produto.getPreco());
        });
        BigDecimal descontoDecimal = new BigDecimal(Double.toString(this.desconto));
        this.valorTotal = this.valorTotal.subtract(this.valorTotal.multiply(descontoDecimal));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
