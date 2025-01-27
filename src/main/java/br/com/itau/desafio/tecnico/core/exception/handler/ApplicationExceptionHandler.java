package br.com.itau.desafio.tecnico.core.exception.handler;

import br.com.itau.desafio.tecnico.core.exception.HorarioTransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.TransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.ValorInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(TransacaoInvalidaException.class)
    public ExceptionBuilder handleCorpoJsonInvalido(final TransacaoInvalidaException transacaoInvalidaException) {
        return ExceptionBuilder.builder()
                .timestamp(LocalDateTime.now())
                .status(422)
                .titulo("Request invalida!")
                .build();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValorInvalidoException.class)
    public ExceptionBuilder handleValorTransacaoInvalido(final ValorInvalidoException valorInvalidoException) {
        return ExceptionBuilder.builder()
                .timestamp(LocalDateTime.now())
                .status(422)
                .titulo("A transação fornecida possui um valor inválido!")
                .build();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HorarioTransacaoInvalidaException.class)
    public ExceptionBuilder handleHorarioTransacaoInvalida(final HorarioTransacaoInvalidaException horarioTransacaoInvalidaException) {
        return ExceptionBuilder.builder()
                .timestamp(LocalDateTime.now())
                .status(422)
                .titulo("O valor fornecido no horário da transação esta inválido!")
                .build();
    }

}
