package br.com.alura.orcamento.dto.receita;

import br.com.alura.orcamento.model.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoReceita(
        String descricao,
        BigDecimal valor,
        //@Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}", message = "A data deve ser escrita no formato DD/MM/AAAA")
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate data
) {

    public DadosAtualizacaoReceita(Receita receita) {
        this(receita.getDescricao(), receita.getValor(), receita.getData());
    }

}
