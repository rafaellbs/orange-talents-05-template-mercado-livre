package br.com.zupacademy.rafael.treinomercadolivre.novaopniaoproduto;

import br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {}

    public Opiniao(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
                   @NotBlank @Length(max = 500) String descricao, @NotNull @Valid Usuario usuario,
                   @NotNull @Valid Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;

        Assert.notNull(produto, "É preciso ter o produto para fazer uma opinião.");
        Assert.notNull(usuario, "É preciso ter o usuário para fazer uma opinião");
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario="
                + usuario + ", produto=" + produto + "]";
    }
}