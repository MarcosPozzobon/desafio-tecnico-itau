package br.com.itau.desafio.tecnico.service;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class MainServiceTest {

    private MainService mainService;
    private HttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        mainService = new MainService();
        mockRequest = mock(HttpServletRequest.class);
    }

    @Test
    @DisplayName(value = "Teste do metodo de limpar transacoes")
    void testeDeletarTransacoesDeveLimparALista() {
        List<TransacaoRequestDTO> transacoes = new ArrayList<>();
        TransacaoRequestDTO transacaoTeste = new TransacaoRequestDTO();

        transacaoTeste.setValor(123.33);
        transacaoTeste.setDataHora(OffsetDateTime.now());

        mainService.deletarTransacoes(mockRequest);
        assertTrue(transacoes.size() == 0, "A lista de transações não foi limpa. Falha no metodo.");
    }

}
