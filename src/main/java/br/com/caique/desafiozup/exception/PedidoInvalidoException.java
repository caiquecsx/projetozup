package br.com.caique.desafiozup.exception;

public class PedidoInvalidoException extends RuntimeException{
    public PedidoInvalidoException(String mensagem){
        super(mensagem);
    }
}
