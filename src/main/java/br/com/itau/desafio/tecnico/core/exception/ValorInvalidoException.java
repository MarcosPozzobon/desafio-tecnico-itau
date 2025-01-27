package br.com.itau.desafio.tecnico.core.exception;

public class ValorInvalidoException extends RuntimeException{

    public ValorInvalidoException(){}

    public ValorInvalidoException(String msg){
        super(msg);
    }

}
