package br.com.zupacademy.rafael.treinomercadolivre.cadasdastrocategoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated

    public Categoria() {
    }

    public Categoria(@NotNull String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoriaMae=" + categoriaMae +
                '}';
    }
}
