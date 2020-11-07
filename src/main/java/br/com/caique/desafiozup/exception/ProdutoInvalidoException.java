package br.com.caique.desafiozup.exception;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
