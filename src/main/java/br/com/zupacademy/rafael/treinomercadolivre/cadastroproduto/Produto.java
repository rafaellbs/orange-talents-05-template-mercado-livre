package br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto;

import br.com.zupacademy.rafael.treinomercadolivre.cadasdastrocategoria.Categoria;
import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @Min(0)
    @NotNull
    private Long quantidadeDisponivel;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    @Size(min = 3)
    @Valid
    @NotNull
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<CaracteristicaProduto>();

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario usuario;

    @NotNull
    private LocalDateTime instanteDaCriacao = LocalDateTime.now();

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @Min(0) @NotNull Long quantidadeDisponivel,
                   @Size(min = 3) @Valid @NotNull Collection<NovaCaracteristicaRequest> caracteristicas,
                   @NotBlank @Length(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
                   @NotNull @Valid Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        Set<CaracteristicaProduto> caracteristicasProduto = caracteristicas
                .stream()
                .map(c -> c.toModel(this))
                .collect(Collectors.toSet());

        Assert.isTrue(caracteristicasProduto.size() >= 3, "É preciso de no mínimo 3 característica para cadastrar um produto.");

        this.caracteristicas = caracteristicasProduto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidadeDisponivel="
                + quantidadeDisponivel + ", caracteristicas=" + caracteristicas + ", descricao=" + descricao
                + ", categoria=" + categoria + ", Usuario=" + usuario + ", Instante de Criação=" + instanteDaCriacao + "]";
    }
}
