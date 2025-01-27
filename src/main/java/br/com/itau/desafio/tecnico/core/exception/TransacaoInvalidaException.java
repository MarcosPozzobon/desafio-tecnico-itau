package br.com.itau.desafio.tecnico.core.exception;

public class TransacaoInvalidaException extends RuntimeException{

    public TransacaoInvalidaException(){}

    public TransacaoInvalidaException(String msg){
        super(msg);
    }

}
