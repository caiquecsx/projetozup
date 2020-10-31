package br.com.caique.desafiozup.model;

public class Produto {
    private String descricao;
    // SKU -> fabricante – produto – modelo – código – endereçamento de estoque
    private Double peso;
    private Dimensoes dimensoes;
    private Fabricante fabricante;

    public Produto() { }

    public Produto(String descricao, Double peso, Dimensoes dimensoes, Fabricante fabricante) {
        this.descricao = descricao;
        this.peso = peso;
        this.dimensoes = dimensoes;
        this.fabricante = fabricante;
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

    public Dimensoes getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(Dimensoes dimensoes) {
        this.dimensoes = dimensoes;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}
