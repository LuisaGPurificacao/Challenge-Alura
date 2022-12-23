package br.com.alura.orcamento.controller;

import br.com.alura.orcamento.dto.usuario.DadosUsuario;
import br.com.alura.orcamento.infra.security.DadosToken;
import br.com.alura.orcamento.infra.security.TokenService;
import br.com.alura.orcamento.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosToken> login(@RequestBody DadosUsuario dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = service.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }

}
