package br.com.alura.orcamento.infra.validation;

import br.com.alura.orcamento.dto.receita.DadosCadastroReceita;
import br.com.alura.orcamento.model.Receita;
import br.com.alura.orcamento.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Month;
import java.util.List;

public class ValidarReceitaIgual {

    public static void validar(DadosCadastroReceita dados, ReceitaRepository repository) {
        List<Receita> receitasDescricaoIguais = repository.findByDescricao(dados.descricao());
        for (Receita receita : receitasDescricaoIguais) {
            Month month = receita.getData().getMonth();
            Month month2 = dados.data().getMonth();
            if (month == month2)
                throw new RuntimeException("Não pode ter uma receita com a mesma descrição no mesmo mês.");
        }
    }

}
