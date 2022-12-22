package br.com.alura.orcamento.controller;

import br.com.alura.orcamento.dto.despesa.DadosAtualizacaoDespesa;
import br.com.alura.orcamento.dto.despesa.DadosCadastroDespesa;
import br.com.alura.orcamento.dto.despesa.DadosListagemDespesa;
import br.com.alura.orcamento.infra.validation.ValidarDespesaIgual;
import br.com.alura.orcamento.model.Despesa;
import br.com.alura.orcamento.repository.DespesaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    DespesaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemDespesa> cadastrar(@RequestBody @Valid DadosCadastroDespesa dados, UriComponentsBuilder uriBuilder) {
        ValidarDespesaIgual.validar(dados, repository);
        Despesa despesa = new Despesa(dados);
        repository.save(despesa);

        URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemDespesa(despesa));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemDespesa>> listar(@PageableDefault(size = 10, sort = "data")
                                                             Pageable paginacao) {
        Page<DadosListagemDespesa> despesas = repository.findAll(paginacao).map(DadosListagemDespesa::new);
        return ResponseEntity.ok(despesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemDespesa> listarPorId(@PathVariable Long id) {
        Optional<Despesa> opcional = repository.findById(id);
        if (opcional.isEmpty())
            throw new RuntimeException("Despesa não encontrada");
        Despesa despesa = opcional.get();
        return ResponseEntity.ok(new DadosListagemDespesa(despesa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemDespesa> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoDespesa dados) {
        ValidarDespesaIgual.validarAtualizacao(id, dados, repository);
        Despesa despesa = repository.getReferenceById(id);
        despesa.atualizar(dados);
        return ResponseEntity.ok(new DadosListagemDespesa(despesa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
