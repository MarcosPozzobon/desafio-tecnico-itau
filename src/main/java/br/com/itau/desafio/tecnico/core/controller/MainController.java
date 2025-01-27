package br.com.itau.desafio.tecnico.core.controller;

import br.com.itau.desafio.tecnico.core.dto.request.TransacaoRequestDTO;
import br.com.itau.desafio.tecnico.core.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/main")
public class MainController {

    private final MainService mainService;

    private MainController(MainService mainService){
        this.mainService = mainService;
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> salvarTransacoes(final TransacaoRequestDTO transacaoRequestDTO){
        //TODO
        return null;
    }

}
