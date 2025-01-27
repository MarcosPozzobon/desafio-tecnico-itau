package br.com.itau.desafio.tecnico.core.exception.handler;

import java.time.LocalDateTime;


public class ExceptionBuilder {

    private String titulo;

    private Integer status;


    private LocalDateTime timestamp;

    public ExceptionBuilder(){}

    public ExceptionBuilder(String titulo, Integer status, LocalDateTime timestamp) {
        this.titulo = titulo;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder {
        private String titulo;
        private Integer status;
        private String detalhes;
        private LocalDateTime timestamp;

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionBuilder build() {
            return new ExceptionBuilder(titulo, status,timestamp);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
