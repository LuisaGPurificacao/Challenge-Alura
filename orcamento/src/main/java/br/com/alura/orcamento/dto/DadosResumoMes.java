package br.com.alura.orcamento.dto;

import java.math.BigDecimal;
import java.util.List;

public record DadosResumoMes(
        BigDecimal valorTotalReceitas,
        BigDecimal valorTotalDespesas,
        BigDecimal saldoFinal,
        List<DadosValorPorCategoriaInterface> valorPorCategoria
) {

    public DadosResumoMes(ResumoMes dados) {
        this(dados.getValorTotalReceitas(), dados.getValorTotalDespesas(),
                dados.calcularSaldoFinal(dados.getValorTotalReceitas(), dados.getValorTotalDespesas()),
                dados.getValorPorCategoria());
    }

}
