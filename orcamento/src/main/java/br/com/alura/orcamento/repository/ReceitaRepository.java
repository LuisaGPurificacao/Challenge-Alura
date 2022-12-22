package br.com.alura.orcamento.repository;

import br.com.alura.orcamento.model.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    public List<Receita> findByDescricao(String descricao);

    public Page<Receita> findByDescricaoContains(String descricao, Pageable paginacao);

    @Query(value = "select * from receitas where (YEAR(data) = ? and MONTH(data) = ?)", nativeQuery = true)
    public Page<Receita> findByAnoeMes(String ano, String mes, Pageable paginacao);

}
