package br.com.itau.desafio.tecnico.core.exception;

public class HorarioTransacaoInvalidaException extends RuntimeException{

    public HorarioTransacaoInvalidaException(){}

    public HorarioTransacaoInvalidaException(String msg){
        super(msg);
    }

}
