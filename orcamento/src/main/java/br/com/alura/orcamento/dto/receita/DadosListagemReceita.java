package br.com.alura.orcamento.dto.receita;

import br.com.alura.orcamento.model.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosListagemReceita(String descricao, BigDecimal valor,
                                   @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
                                   LocalDate data
) {

    public DadosListagemReceita(Receita receita) {
        this(receita.getDescricao(), receita.getValor(), receita.getData());
    }

}
