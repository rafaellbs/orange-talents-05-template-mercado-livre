package br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto;

import br.com.zupacademy.rafael.treinomercadolivre.cadasdastrocategoria.Categoria;
import br.com.zupacademy.rafael.treinomercadolivre.cadastroimagemproduto.ImagemProduto;
import br.com.zupacademy.rafael.treinomercadolivre.novaopniaoproduto.Opiniao;
import br.com.zupacademy.rafael.treinomercadolivre.novapergunta.Pergunta;
import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
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

    @NotNull
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<ImagemProduto>();

    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<Pergunta>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<Opiniao>();

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
        Assert.isNull(usuario, "O usuário não pode ser nulo");
        Assert.isNull(categoria, "Você precisa informar uma categoria");

        this.caracteristicas = caracteristicasProduto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidadeDisponivel="
                + quantidadeDisponivel + ", caracteristicas=" + caracteristicas + ", descricao=" + descricao
                + ", categoria=" + categoria + ", Usuario=" + usuario + ", Instante de Criação=" + instanteDaCriacao + "" +
                ", imagens=" + imagens + "]";
    }

    public Long idUsuarioPertenceAProduto() {
        return usuario.getId();
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public boolean pertenceAoUsuario(Usuario usuarioAutenticado) {
        return this.usuario.equals(usuarioAutenticado);
    }

    public <T> Set<T> mapeiaCaracteristicas(
            Function<CaracteristicaProduto, T> mapFunction) {
        return this.caracteristicas.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> mapFunction) {
        return this.imagens.stream().map(mapFunction).collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
    }


    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }
}
