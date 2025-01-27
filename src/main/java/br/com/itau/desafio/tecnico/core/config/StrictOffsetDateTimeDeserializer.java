package br.com.itau.desafio.tecnico.core.config;

import br.com.itau.desafio.tecnico.core.exception.TransacaoInvalidaException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StrictOffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
                    .withZone(ZoneOffset.UTC);

    @Override
    public OffsetDateTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String value = parser.getValueAsString();
        if (value == null) {
            throw new TransacaoInvalidaException("dataHora deve ser uma valor no formato yyyy-MM-dd'T'HH:mm:ssXXX");
        }
        try {
            return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new TransacaoInvalidaException("Formato de data inválido. Use yyyy-MM-dd'T'HH:mm:ssXXX (ex: 2023-10-05T15:30:00-03:00)");
        }
    }
}
