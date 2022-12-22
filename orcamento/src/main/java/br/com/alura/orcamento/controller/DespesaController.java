package br.com.alura.orcamento.controller;

import br.com.alura.orcamento.dto.despesa.DadosCadastroDespesa;
import br.com.alura.orcamento.dto.despesa.DadosDetalhamentoDespesa;
import br.com.alura.orcamento.infra.validation.ValidarDespesaIgual;
import br.com.alura.orcamento.model.Despesa;
import br.com.alura.orcamento.repository.DespesaRepository;
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
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    DespesaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoDespesa> cadastrar(@RequestBody @Valid DadosCadastroDespesa dados, UriComponentsBuilder uriBuilder) {
        ValidarDespesaIgual.validar(dados, repository);
        Despesa despesa = new Despesa(dados);
        repository.save(despesa);

        URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoDespesa(despesa));
    }

}
