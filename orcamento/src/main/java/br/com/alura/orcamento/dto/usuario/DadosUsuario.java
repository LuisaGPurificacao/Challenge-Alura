package br.com.alura.orcamento.dto.usuario;

import br.com.alura.orcamento.model.Usuario;

public record DadosUsuario(String login, String senha) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getLogin(), usuario.getSenha());
    }

}
