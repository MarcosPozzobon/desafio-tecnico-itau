package br.com.itau.desafio.tecnico.core.dto.request;

import br.com.itau.desafio.tecnico.core.config.StrictOffsetDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.OffsetDateTime;


public class TransacaoRequestDTO {

    private Double valor;

    @NotNull(message = "dataHora não pode ser nula")
    @PastOrPresent(message = "dataHora deve ser no passado ou presente")
    @JsonDeserialize(using = StrictOffsetDateTimeDeserializer.class)
    private OffsetDateTime dataHora;

    public TransacaoRequestDTO(){}

    public TransacaoRequestDTO(Double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public static class Builder {
        private Double valor;
        private OffsetDateTime dataHora;

        public Builder valor(Double valor) {
            this.valor = valor;
            return this;
        }

        public Builder dataHora(OffsetDateTime dataHora) {
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
