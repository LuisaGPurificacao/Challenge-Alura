package br.com.alura.orcamento.repository;

import br.com.alura.orcamento.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    public List<Receita> findByDescricao(String descricao);

}
