package br.com.itau.desafio.tecnico.core.service;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.dto.response.EstatisticaResponseDTO;
import br.com.itau.desafio.tecnico.core.exception.HorarioTransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.TransacaoInvalidaException;
import br.com.itau.desafio.tecnico.core.exception.ValorInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private static List<TransacaoRequestDTO> transacoes = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

    public void deletarTransacoes(HttpServletRequest request) {
        if (!transacoes.isEmpty()) {
            transacoes.clear();
            logInfo("O serviço de exclusão de transações foi chamado", request);
        }
    }

    public EstatisticaResponseDTO listarEstatisticas(Integer tempoStatsMinutos, HttpServletRequest request) {
        long startTime = System.nanoTime();

        OffsetDateTime tempoLimite = OffsetDateTime.now().minusMinutes(tempoStatsMinutos);

        List<TransacaoRequestDTO> transacoesFiltradas = transacoes.stream()
                .filter(transacao -> transacao.getDataHora().isAfter(tempoLimite) && transacao.getDataHora().isBefore(OffsetDateTime.now()))
                .collect(Collectors.toList());

        DoubleSummaryStatistics stats = transacoesFiltradas.stream()
                .mapToDouble(TransacaoRequestDTO::getValor)
                .summaryStatistics();

        EstatisticaResponseDTO estatisticas = new EstatisticaResponseDTO();
        estatisticas.setCount(stats.getCount());
        estatisticas.setSum(stats.getSum());
        estatisticas.setAvg(stats.getAverage());
        estatisticas.setMin(stats.getCount() > 0 ? stats.getMin() : 0.0);
        estatisticas.setMax(stats.getCount() > 0 ? stats.getMax() : 0.0);

        long endTime = System.nanoTime();

        long durationInMillis = (endTime - startTime) / 1_000_000;

        LOGGER.info("Tempo levado para o cálculo das estatísticas: {} ms", durationInMillis);

        logInfo("O serviço de listagem de estatísticas foi chamado", request);

        return estatisticas;
    }


    public void salvarTransacao(final TransacaoRequestDTO transacaoRequestDTO, HttpServletRequest request) {
        if (transacaoRequestDTO == null || !isTransacaoValida(transacaoRequestDTO)) {
            throw new TransacaoInvalidaException();
        }

        LOGGER.info("O serviço de inclusão de transações foi chamado em {} às {}.",
                LocalDateTime.now(), transacaoRequestDTO);
        transacoes.add(transacaoRequestDTO);
    }

    private boolean isTransacaoValida(final TransacaoRequestDTO transacaoRequestDTO) {
        if (!isValorValido(transacaoRequestDTO.getValor())) {
            throw new ValorInvalidoException();
        }

        if (!isDataTransacaoValida(transacaoRequestDTO.getDataHora())) {
            throw new HorarioTransacaoInvalidaException();
        }

        return true;
    }

    private boolean isDataTransacaoValida(OffsetDateTime dataTransacao) {
        if (dataTransacao == null || dataTransacao.isAfter(OffsetDateTime.now())) {
            throw new HorarioTransacaoInvalidaException();
        }
        return true;
    }

    private boolean isValorValido(Double valor) {
        if (valor == null || valor < 0) {
            throw new ValorInvalidoException();
        }
        return true;
    }

    private void logInfo(String mensagem, HttpServletRequest request) {
        String host = request.getServerName();
        String requestURL = request.getRequestURL().toString();

        LOGGER.info("{} em {} às {}. Host: {}, URL: {}",
                mensagem,
                this.getClass().getName(),
                LocalDateTime.now(),
                host,
                requestURL);
    }
}
