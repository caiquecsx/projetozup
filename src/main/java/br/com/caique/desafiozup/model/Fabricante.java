package br.com.caique.desafiozup.model;

public class Fabricante {
    private String nome;

    public Fabricante() { }

    public Fabricante(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
