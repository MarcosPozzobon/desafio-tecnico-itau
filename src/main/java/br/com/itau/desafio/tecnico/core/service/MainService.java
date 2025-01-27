package br.com.itau.desafio.tecnico.core.service;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.exception.HorarioTransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.TransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.ValorInvalidoException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private static List<TransacaoRequestDTO> transacoes = new ArrayList<>();

    public void salvarTransacao(final TransacaoRequestDTO transacaoRequestDTO){
        if(transacaoRequestDTO == null || !isTransacaoValida(transacaoRequestDTO)){
            throw new TransacaoInvalidaException();
        }

        transacoes.add(transacaoRequestDTO);
        System.out.println("Provavelmente salvei a transacao!");
    }

    public boolean isTransacaoValida(final TransacaoRequestDTO transacaoRequestDTO){

        if(!isValorValido(transacaoRequestDTO.getValor())){
            throw new ValorInvalidoException();
        }

        if(!isDataTransacaoValida(transacaoRequestDTO.getDataHora())){
            throw new HorarioTransacaoInvalidaException();
        }

        return true;

    }

    private boolean isDataTransacaoValida(OffsetDateTime dataTransacao) {
        if(dataTransacao == null){
            throw new HorarioTransacaoInvalidaException();
        }
        return !dataTransacao.isAfter(OffsetDateTime.now());
    }

    private boolean isValorValido(Double valor) {
        if(valor == null || valor < 0){
            throw new ValorInvalidoException();
        }
        return true;
    }

}
