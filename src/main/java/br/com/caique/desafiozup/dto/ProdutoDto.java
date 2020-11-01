package br.com.caique.desafiozup.dto;

import br.com.caique.desafiozup.model.Fabricante;
import br.com.caique.desafiozup.model.Produto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public class ProdutoDto {
    private Long id;
    private String descricao;
    private Fabricante fabricante;
    private BigDecimal preco;

    public ProdutoDto() { }

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.fabricante = produto.getFabricante();
        this.preco = produto.getPreco();
    }

    public Page<ProdutoDto> converter(Page<Produto> produtos){
        return produtos.map(ProdutoDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
