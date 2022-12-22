package br.com.alura.orcamento.dto.receita;

import br.com.alura.orcamento.infra.validation.ValidarReceitaIgual;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record DadosCadastroReceita(
        @NotBlank(message = "A descrição é obrigatória")
        String descricao,
        @NotNull(message = "O valor é obrigatório")
        BigDecimal valor,
        //@Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}", message = "A data deve ser escrita no formato DD/MM/AAAA")
        @NotNull(message = "A data é obrigatória")
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate data
) {
}
