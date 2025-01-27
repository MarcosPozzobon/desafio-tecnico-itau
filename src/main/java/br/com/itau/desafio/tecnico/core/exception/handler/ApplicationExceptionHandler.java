package br.com.itau.desafio.tecnico.core.exception.handler;

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
                .detalhes(transacaoInvalidaException.getMessage())
                .status(422)
                .titulo("Request invalida!")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValorInvalidoException.class)
    public ExceptionBuilder handleValorTransacaoInvalido(final ValorInvalidoException valorInvalidoException) {
        return ExceptionBuilder.builder()
                .timestamp(LocalDateTime.now())
                .detalhes(valorInvalidoException.getMessage())
                .status(400)
                .titulo("Valor invalido da transacao!")
                .build();
    }

}
