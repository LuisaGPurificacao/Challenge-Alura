package br.com.alura.orcamento.dto.receita;

import br.com.alura.orcamento.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoReceita(Long id, String descricao, BigDecimal valor, LocalDate data) {

    public DadosDetalhamentoReceita(Receita receita) {
        this(receita.getId(), receita.getDescricao(), receita.getValor(), receita.getData());
    }

}
