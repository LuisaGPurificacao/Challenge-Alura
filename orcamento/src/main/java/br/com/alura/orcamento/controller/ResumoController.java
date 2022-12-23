package br.com.alura.orcamento.controller;

import br.com.alura.orcamento.dto.resumo.DadosResumoMes;
import br.com.alura.orcamento.dto.resumo.DadosValorPorCategoriaInterface;
import br.com.alura.orcamento.dto.resumo.ResumoMes;
import br.com.alura.orcamento.repository.DespesaRepository;
import br.com.alura.orcamento.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    ReceitaRepository receitaRepository;
    @Autowired
    DespesaRepository despesaRepository;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<DadosResumoMes> listar(@PathVariable String ano, @PathVariable String mes) {
        BigDecimal valorReceitas = receitaRepository.valorReceitas(mes, ano);
        BigDecimal valorDespesas = despesaRepository.valorDespesas(mes, ano);
        List<DadosValorPorCategoriaInterface> valorPorCategorias = despesaRepository.retornarValorPorCategoria(mes, ano);
        return ResponseEntity.ok(new DadosResumoMes(new ResumoMes(valorReceitas, valorDespesas, valorPorCategorias)));
    }

}
