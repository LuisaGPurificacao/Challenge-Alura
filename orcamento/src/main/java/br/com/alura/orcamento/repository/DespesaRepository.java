package br.com.alura.orcamento.repository;

import br.com.alura.orcamento.dto.DadosValorPorCategoriaInterface;
import br.com.alura.orcamento.model.Despesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    public List<Despesa> findByDescricao(String descricao);

    public Page<Despesa> findByDescricaoContains(String descricao, Pageable paginacao);

    @Query(value = "select * from despesas where (YEAR(data) = ? and MONTH(data) = ?)", nativeQuery = true)
    public Page<Despesa> findByAnoeMes(String ano, String mes, Pageable paginacao);

    @Query(value = "select sum(valor) as valorDespesas from despesas where (MONTH(data) = ? and YEAR(data) = ?)", nativeQuery = true)
    public BigDecimal valorDespesas(String mes, String ano);

    @Query(value = "select sum(valor) as valor, categoria from despesas where (MONTH(data) = ? and YEAR(data) = ?) group by categoria", nativeQuery = true)
    public List<DadosValorPorCategoriaInterface> retornarValorPorCategoria(String mes, String ano);

}
