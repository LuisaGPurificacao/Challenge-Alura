package br.com.alura.orcamento.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroUsuario(
        @NotBlank(message = "O login é obrigatório")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "São necessários 8 caracteres no mínimo")
        String senha) {
}
