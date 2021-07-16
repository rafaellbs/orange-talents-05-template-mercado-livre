package br.com.zupacademy.rafael.treinomercadolivre.cadasdastrocategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovaCategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/categorias")
    @Transactional
    public String cadastrar(@RequestBody @Valid NovaCategoriaRequest request){
        Categoria novaCategoria = request.toModel(categoriaRepository);

        categoriaRepository.save(novaCategoria);

        return novaCategoria.toString();
    }
}
