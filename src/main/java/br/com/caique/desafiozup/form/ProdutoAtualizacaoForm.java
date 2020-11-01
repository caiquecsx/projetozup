package br.com.caique.desafiozup.form;

import br.com.caique.desafiozup.model.Dimensoes;
import br.com.caique.desafiozup.model.Fabricante;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.ProdutoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoAtualizacaoForm {

    @NotEmpty
    @NotNull
    private String descricao;
    // SKU -> fabricante – produto – modelo – código – endereçamento de estoque
    @NotNull
    private Double peso;
    @NotNull
    private BigDecimal preco;

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getOne(id);
        produto.setDescricao(this.descricao);
        produto.setPeso(this.peso);
        produto.setPreco(this.preco);

        return produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
