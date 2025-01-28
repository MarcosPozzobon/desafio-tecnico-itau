package br.com.itau.desafio.tecnico.service;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.dto.response.EstatisticaResponseDTO;
import br.com.itau.desafio.tecnico.core.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainServiceTest {

    private MainService mainService;
    private HttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        mainService = new MainService();
        mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getRemoteAddr()).thenReturn("127.0.0.1");
        when(mockRequest.getHeader("User-Agent")).thenReturn("JUnit-Test-Agent");
        when(mockRequest.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/exemplo-url"));

    }

    @Test
    @DisplayName(value = "Teste do metodo de estatisticas")
    void listarEstatisticas_deveRetornarEstatisticasCorretas() {
        List<TransacaoRequestDTO> transacoesTeste = new ArrayList<>();

        TransacaoRequestDTO transacao1 = new TransacaoRequestDTO();
        transacao1.setValor(1.0);
        transacao1.setDataHora(OffsetDateTime.now().minusMinutes(1));

        TransacaoRequestDTO transacao2 = new TransacaoRequestDTO();
        transacao2.setValor(2.0);
        transacao2.setDataHora(OffsetDateTime.now().minusMinutes(2));

        TransacaoRequestDTO transacao3 = new TransacaoRequestDTO();
        transacao3.setValor(3.0);
        transacao3.setDataHora(OffsetDateTime.now().minusMinutes(5));

        transacoesTeste.add(transacao1);
        transacoesTeste.add(transacao2);
        transacoesTeste.add(transacao3);

        EstatisticaResponseDTO estatisticas = mainService.listarEstatisticas(15, mockRequest, transacoesTeste);

        assertEquals(3, estatisticas.getCount(), "Deve haver 3 transações no intervalo de tempo");
        assertEquals(6.0, estatisticas.getSum(), "A soma dos valores deve ser 6.0");
        assertEquals(2.0, estatisticas.getAvg(), "A média dos valores deve ser 2.0");
        assertEquals(1.0, estatisticas.getMin(), "O menor valor deve ser 1.0");
        assertEquals(3.0, estatisticas.getMax(), "O maior valor deve ser 3.0");
    }

    @Test
    @DisplayName(value = "Teste do metodo de salvar uma transacao")
    void testeSalvarTransacaoDeveSomarALista() {
        List<TransacaoRequestDTO> transacoes = new ArrayList<>();
        TransacaoRequestDTO transacaoTeste = new TransacaoRequestDTO();

        transacaoTeste.setValor(123.23);
        transacaoTeste.setDataHora(OffsetDateTime.now());

        mainService.salvarTransacao(transacaoTeste,mockRequest, transacoes);
        assertTrue(transacoes.size() == 1, "Falha ao incluir nova transacao na lista. Falha no metodo.");
    }

    @Test
    @DisplayName(value = "Teste do metodo de limpar lista de transacoes")
    void testeDeletarTransacoesDeveLimparALista() {
        List<TransacaoRequestDTO> transacoes = new ArrayList<>();
        TransacaoRequestDTO transacaoTeste = new TransacaoRequestDTO();

        transacaoTeste.setValor(123.33);
        transacaoTeste.setDataHora(OffsetDateTime.now());

        mainService.deletarTransacoes(mockRequest, transacoes);
        assertTrue(transacoes.size() == 0, "A lista de transações não foi limpa. Falha no metodo.");
    }

}
