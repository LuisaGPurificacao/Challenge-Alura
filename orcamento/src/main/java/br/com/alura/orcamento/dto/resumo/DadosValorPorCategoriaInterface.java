package br.com.alura.orcamento.dto.resumo;

import br.com.alura.orcamento.model.Categoria;

import java.math.BigDecimal;

public interface DadosValorPorCategoriaInterface {

    Categoria getCategoria();

    BigDecimal getValor();

}
