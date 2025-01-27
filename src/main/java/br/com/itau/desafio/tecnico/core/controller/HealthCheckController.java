package br.com.itau.desafio.tecnico.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/healthcheck")
public class HealthCheckController {

    @GetMapping
    public Map<String, Object> healthCheckEndpoint() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("timestamp", LocalDateTime.now());
        map.put("msg", "api funcionando normalmente!");

        return map;
    }

}
