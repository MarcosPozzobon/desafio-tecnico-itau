package br.com.itau.desafio.tecnico.core.controller;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.dto.response.EstatisticaResponseDTO;
import br.com.itau.desafio.tecnico.core.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/main")
public class MainController {

    private final MainService mainService;

    private static List<TransacaoRequestDTO> transacoes = new ArrayList<>();

    private MainController(MainService mainService){
        this.mainService = mainService;
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaResponseDTO> obterEstatisticas(@RequestParam(defaultValue = "60") Integer tempoStatsMinutos, HttpServletRequest request){
        var estatisticas = mainService.listarEstatisticas(tempoStatsMinutos, request, transacoes);
        return ResponseEntity.status(200).body(estatisticas);
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> salvarTransacoes(final @Valid @RequestBody TransacaoRequestDTO transacaoRequestDTO, HttpServletRequest request, List<TransacaoRequestDTO> transacoes){
        mainService.salvarTransacao(transacaoRequestDTO, request, transacoes);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletarTransacoes(HttpServletRequest request){
        mainService.deletarTransacoes(request, transacoes);
        return ResponseEntity.status(200).build();
    }

}
