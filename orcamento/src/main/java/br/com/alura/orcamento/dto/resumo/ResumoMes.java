package br.com.alura.orcamento.dto.resumo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumoMes {
    private BigDecimal valorTotalReceitas;
    private BigDecimal valorTotalDespesas;
    private List<DadosValorPorCategoriaInterface> valorPorCategoria;

    public BigDecimal calcularSaldoFinal(BigDecimal receitas, BigDecimal despesas) {
        if (despesas == null)
            return receitas;
        return (receitas.subtract(despesas));
    }

}
