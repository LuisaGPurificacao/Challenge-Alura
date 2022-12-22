package br.com.alura.orcamento.dto.despesa;

import br.com.alura.orcamento.dto.receita.DadosDetalhamentoReceita;
import br.com.alura.orcamento.model.Categoria;
import br.com.alura.orcamento.model.Despesa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosListagemDespesa(String descricao, BigDecimal valor,
                                   @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
                                   LocalDate data,
                                   Categoria categoria) {

    public DadosListagemDespesa(Despesa despesa) {
        this(despesa.getDescricao(), despesa.getValor(), despesa.getData(), despesa.getCategoria());
    }

}
