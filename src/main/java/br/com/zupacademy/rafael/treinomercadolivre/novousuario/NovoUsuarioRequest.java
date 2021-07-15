package br.com.zupacademy.rafael.treinomercadolivre.novousuario;

import br.com.zupacademy.rafael.treinomercadolivre.validacao.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "NovoUsuarioRequest{" +
                "login='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Usuario toModel(){
        return new Usuario(email, new SenhaLimpa(senha));
    }
}
