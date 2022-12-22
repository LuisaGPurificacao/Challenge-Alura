package br.com.alura.orcamento.controller;

import br.com.alura.orcamento.dto.receita.DadosCadastroReceita;
import br.com.alura.orcamento.dto.receita.DadosDetalhamentoReceita;
import br.com.alura.orcamento.infra.validation.ValidarReceitaIgual;
import br.com.alura.orcamento.model.Receita;
import br.com.alura.orcamento.repository.ReceitaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroReceita dados, UriComponentsBuilder uriBuilder) {
        ValidarReceitaIgual.validar(dados, repository);
        Receita receita = new Receita(dados);
        repository.save(receita);

        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoReceita(receita));
    }

}
