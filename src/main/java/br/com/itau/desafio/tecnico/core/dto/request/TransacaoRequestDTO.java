package br.com.itau.desafio.tecnico.core.dto.request;

import java.time.LocalDateTime;


public class TransacaoRequestDTO {

    private Double valor;

    private LocalDateTime dataHora;

    public TransacaoRequestDTO(){}

    public TransacaoRequestDTO(Double valor, LocalDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public static class Builder {
        private Double valor;
        private LocalDateTime dataHora;

        public Builder valor(Double valor) {
            this.valor = valor;
            return this;
        }

        public Builder dataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
            return this;
        }

        public TransacaoRequestDTO build() {
            return new TransacaoRequestDTO(valor, dataHora);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
