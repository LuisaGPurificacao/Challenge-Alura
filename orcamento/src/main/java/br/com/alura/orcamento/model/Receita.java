package br.com.alura.orcamento.model;

import br.com.alura.orcamento.dto.receita.DadosAtualizacaoReceita;
import br.com.alura.orcamento.dto.receita.DadosCadastroReceita;
import br.com.alura.orcamento.infra.validation.ValidarReceitaIgual;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "receitas")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public Receita(DadosCadastroReceita dados) {
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.data = dados.data();
    }

    public void atualizarInformacoes(DadosAtualizacaoReceita dados) {
        if (dados.descricao() != null)
            this.descricao = dados.descricao();
        if (dados.valor() != null)
            this.valor = dados.valor();
        if (dados.data() != null)
            this.data = dados.data();
    }

}
