package br.com.alura.orcamento.repository;

import br.com.alura.orcamento.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    public List<Despesa> findByDescricao(String descricao);

    public Page<Despesa> findByDescricaoContains(String descricao, Pageable paginacao);

}
