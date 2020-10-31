package br.com.caique.desafiozup.model;

public class Dimensoes {
    private Double altura;
    private Double largura;
    private Double profundidade;

    public Dimensoes() { }

    public Dimensoes(Double altura, Double largura, Double profundidade) {
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }
}
