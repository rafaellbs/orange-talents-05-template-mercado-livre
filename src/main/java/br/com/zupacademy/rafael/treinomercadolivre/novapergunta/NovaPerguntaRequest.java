package br.com.zupacademy.rafael.treinomercadolivre.novapergunta;

import br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaPerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "NovaPerguntaRequest [titulo=" + titulo + "]";
    }

    public Pergunta toModel(@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
        return new Pergunta(titulo, usuario, produto);
    }
}
