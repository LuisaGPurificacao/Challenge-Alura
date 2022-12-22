package br.com.alura.orcamento.controller;

import br.com.alura.orcamento.dto.receita.DadosAtualizacaoReceita;
import br.com.alura.orcamento.dto.receita.DadosCadastroReceita;
import br.com.alura.orcamento.dto.receita.DadosDetalhamentoReceita;
import br.com.alura.orcamento.dto.receita.DadosListagemReceita;
import br.com.alura.orcamento.infra.validation.ValidarReceitaIgual;
import br.com.alura.orcamento.model.Receita;
import br.com.alura.orcamento.repository.ReceitaRepository;
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

    @GetMapping
    public ResponseEntity<Page<DadosListagemReceita>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao,
                                                             @RequestParam(name = "descricao", required = false) String descricao) {
        return descricao == null ? ResponseEntity.ok(repository.findAll(paginacao).map(DadosListagemReceita::new))
                : ResponseEntity.ok(repository.findByDescricaoContains(descricao, paginacao).map(DadosListagemReceita::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoReceita> listarPorId(@PathVariable Long id) {
        Optional<Receita> opcional = repository.findById(id);
        if (opcional.isEmpty())
            throw new RuntimeException("Receita não encontrada");
        Receita receita = opcional.get();
        return ResponseEntity.ok(new DadosDetalhamentoReceita(receita));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoReceita> atualizar(@RequestBody @Valid DadosAtualizacaoReceita dados, @PathVariable Long id) {
        ValidarReceitaIgual.validarAtualizacao(id, dados, repository);
        Receita receita = repository.getReferenceById(id);
        receita.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoReceita(receita));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
