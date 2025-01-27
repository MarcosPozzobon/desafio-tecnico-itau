package br.com.itau.desafio.tecnico.core.service;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.dto.response.EstatisticaResponseDTO;
import br.com.itau.desafio.tecnico.core.exception.HorarioTransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.TransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.ValorInvalidoException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private static List<TransacaoRequestDTO> transacoes = new ArrayList<>();

    public void deletarTransacoes(){
        if(!transacoes.isEmpty()){
            transacoes.clear();
        }
    }

    public EstatisticaResponseDTO listarEstatisticas(Integer tempoStatsMinutos) {
        var minutos = tempoStatsMinutos;
        OffsetDateTime umMinutoAtras = OffsetDateTime.now().minusMinutes(minutos);

        List<TransacaoRequestDTO> transacoesUltimoMinuto = transacoes.stream()
                .filter(transacao -> transacao.getDataHora().isAfter(umMinutoAtras)
                        && transacao.getDataHora().isBefore(OffsetDateTime.now()))
                .collect(Collectors.toList());

        DoubleSummaryStatistics stats = transacoesUltimoMinuto.stream()
                .mapToDouble(TransacaoRequestDTO::getValor)
                .summaryStatistics();

        EstatisticaResponseDTO resultadoEstatisticas = new EstatisticaResponseDTO();

        resultadoEstatisticas.setCount(stats.getCount());
        resultadoEstatisticas.setSum(stats.getSum());
        resultadoEstatisticas.setAvg(stats.getAverage());

        resultadoEstatisticas.setMin(stats.getCount() > 0 ? stats.getMin() : 0.0);
        resultadoEstatisticas.setMax(stats.getCount() > 0 ? stats.getMax() : 0.0);


        return resultadoEstatisticas;
    }


    public void salvarTransacao(final TransacaoRequestDTO transacaoRequestDTO){
        if(transacaoRequestDTO == null || !isTransacaoValida(transacaoRequestDTO)){
            throw new TransacaoInvalidaException();
        }

        transacoes.add(transacaoRequestDTO);
    }

    private boolean isTransacaoValida(final TransacaoRequestDTO transacaoRequestDTO){

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
