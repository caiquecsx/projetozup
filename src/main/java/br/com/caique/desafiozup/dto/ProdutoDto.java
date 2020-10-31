package br.com.caique.desafiozup.dto;

import br.com.caique.desafiozup.model.Fabricante;
import br.com.caique.desafiozup.model.Produto;
import org.springframework.data.domain.Page;

public class ProdutoDto {
    private String descricao;
    private Fabricante fabricante;

    public ProdutoDto() { }

    public ProdutoDto(Produto produto) {
        this.descricao = produto.getDescricao();
        this.fabricante = produto.getFabricante();
    }

    public Page<ProdutoDto> converter(Page<Produto> produtos){
        return produtos.map(ProdutoDto::new);
    }

    public String getDescricao() {
        return descricao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }
}
