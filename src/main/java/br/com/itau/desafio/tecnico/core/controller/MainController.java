package br.com.itau.desafio.tecnico.core.controller;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.dto.response.EstatisticaResponseDTO;
import br.com.itau.desafio.tecnico.core.service.MainService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/main")
public class MainController {

    private final MainService mainService;

    private MainController(MainService mainService){
        this.mainService = mainService;
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaResponseDTO> obterEstatisticas(){
        var estatisticas = mainService.listarEstatisticas();
        return ResponseEntity.status(200).body(estatisticas);
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> salvarTransacoes(final @Valid @RequestBody TransacaoRequestDTO transacaoRequestDTO){
        mainService.salvarTransacao(transacaoRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletarTransacoes(){
        mainService.deletarTransacoes();
        return ResponseEntity.status(200).build();
    }

}
