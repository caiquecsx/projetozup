package br.com.caique.desafiozup.form;

import br.com.caique.desafiozup.model.Dimensoes;
import br.com.caique.desafiozup.model.Fabricante;
import br.com.caique.desafiozup.model.Produto;
import br.com.caique.desafiozup.repository.FabricanteRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoForm {

    @NotEmpty @NotNull
    private String descricao;
    // SKU -> fabricante – produto – modelo – código – endereçamento de estoque
    @NotNull
    private Double peso;
    @NotNull
    private Dimensoes dimensoes;
    @NotNull
    private Fabricante fabricante;
    @NotNull
    private BigDecimal preco;

    public ProdutoForm(String descricao, Double peso, Dimensoes dimensoes, Fabricante fabricante, BigDecimal preco) {
        this.descricao = descricao;
        this.peso = peso;
        this.dimensoes = dimensoes;
        this.fabricante = fabricante;
        this.preco = preco;
    }

    public Produto converter(FabricanteRepository fabricanteRepository) {

        if(this.fabricante.getId() != null){
            Optional<Fabricante> fabricante = fabricanteRepository.findById(this.fabricante.getId());
            if(fabricante.isPresent()){
                return new Produto(this.descricao, this.peso, this.dimensoes, fabricante.get(), this.preco);
            }
        }

        return new Produto(this.descricao, this.peso, this.dimensoes, this.fabricante, this.preco);
    }

    @Override
    public String toString() {
        return "ProdutoForm{" +
                "descricao='" + descricao + '\'' +
                ", peso=" + peso +
                ", dimensoes=" + dimensoes.getAltura() +
                ", fabricante=" + fabricante.getNome() +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public Dimensoes getDimensoes() {
        return dimensoes;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

}
