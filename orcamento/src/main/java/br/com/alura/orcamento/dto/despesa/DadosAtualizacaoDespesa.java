package br.com.alura.orcamento.dto.despesa;

import br.com.alura.orcamento.model.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoDespesa(
        String descricao,
        BigDecimal valor,
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate data,
        Categoria categoria
) {
}
