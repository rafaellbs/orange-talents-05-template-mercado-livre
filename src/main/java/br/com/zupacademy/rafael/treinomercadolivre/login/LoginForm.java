package br.com.zupacademy.rafael.treinomercadolivre.login;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public LoginForm(String email, String senha){

    }

    public String getEmail() {
        return email;
    }

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

    public String getSenha() {
        return senha;
    }
}
