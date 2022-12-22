package br.com.alura.orcamento.infra.validation;

import br.com.alura.orcamento.dto.despesa.DadosCadastroDespesa;
import br.com.alura.orcamento.model.Despesa;
import br.com.alura.orcamento.repository.DespesaRepository;

import java.time.Month;
import java.util.List;

public class ValidarDespesaIgual {

    public static void validar(DadosCadastroDespesa dados, DespesaRepository repository) {
        List<Despesa> despesasDescricaoIguais = repository.findByDescricao(dados.descricao());
        for (Despesa despesa : despesasDescricaoIguais) {
            Month month = despesa.getData().getMonth();
            Month month2 = dados.data().getMonth();
            if (month == month2)
                throw new RuntimeException("Não pode ter uma despesa com a mesma descrição no mesmo mês.");
        }
    }

    /**
     public static void validarAtualizacao(Long id, DadosAtualizacaoDespesa dados, DespesaRepository repository) {
     if (dados.descricao() == "" || dados.descricao() == null)
     return;
     Despesa despesa = repository.getReferenceById(id);
     List<Despesa> receitasDescricaoIguais = repository.findByDescricao(dados.descricao());
     for (Despesa d : despesasDescricaoIguais) {
     Month month = d.getData().getMonth();
     Month month2 = despesa.getData().getMonth();
     if (month == month2)
     throw new RuntimeException("Não pode ter uma despesa com a mesma descrição no mesmo mês.");
     }
     }
     **/

}
