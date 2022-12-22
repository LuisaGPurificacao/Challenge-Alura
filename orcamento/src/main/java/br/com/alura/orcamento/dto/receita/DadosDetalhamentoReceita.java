package br.com.alura.orcamento.dto.receita;

import br.com.alura.orcamento.model.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoReceita(String descricao, BigDecimal valor,
                                       @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
                                       LocalDate data) {

    public DadosDetalhamentoReceita(Receita receita) {
        this(receita.getDescricao(), receita.getValor(), receita.getData());
    }

}
