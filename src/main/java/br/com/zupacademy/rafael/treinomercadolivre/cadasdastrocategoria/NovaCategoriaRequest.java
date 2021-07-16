package br.com.zupacademy.rafael.treinomercadolivre.cadasdastrocategoria;

import br.com.zupacademy.rafael.treinomercadolivre.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Nullable
    private Long idCategoriaMae;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCategoriaRequest(String nome){
        this.nome = nome;
    }

    @Nullable
    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    @Override
    public String toString() {
        return "NovaCategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", idCategoriaMae=" + idCategoriaMae +
                '}';
    }

    public Categoria toModel(CategoriaRepository categoriaRepository){
        Categoria categoria= new Categoria(nome);

        if(idCategoriaMae != null){
            Categoria possivelCategoriaMae = categoriaRepository.findById(idCategoriaMae).
                    orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
            categoria.setCategoriaMae(possivelCategoriaMae);
        }

        return categoria;
    }
}
