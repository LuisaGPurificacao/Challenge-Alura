package br.com.alura.orcamento.model;

import br.com.alura.orcamento.dto.despesa.DadosAtualizacaoDespesa;
import br.com.alura.orcamento.dto.despesa.DadosCadastroDespesa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public Despesa(DadosCadastroDespesa dados) {
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.data = dados.data();
    }

    public void atualizar(DadosAtualizacaoDespesa dados) {
        if (dados.descricao() != null)
            this.descricao = dados.descricao();
        if (dados.valor() != null)
            this.valor = dados.valor();
        if (dados.data() != null)
            this.data = dados.data();
    }
}
