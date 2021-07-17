package br.com.zupacademy.rafael.treinomercadolivre.novapergunta;

import br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario usuario;
    @ManyToOne
    @NotNull @Valid
    private Produto produto;

    private LocalDate instante;

    @Deprecated
    public Pergunta() {
    };

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
        this.instante = LocalDate.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario donoDoProduto() {
        return produto.getUsuario();
    }

    public String nomeDoProduto() {
        return produto.getNome();
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Pergunta [id=" + id + ", titulo=" + titulo + ", usuario=" + usuario + ", produto=" + produto
                + ", instante=" + instante + "]";
    }

}
