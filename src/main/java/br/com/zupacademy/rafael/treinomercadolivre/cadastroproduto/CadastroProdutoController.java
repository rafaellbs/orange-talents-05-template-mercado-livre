package br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto;

import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuarioAutenticado =(Usuario) authentication.getPrincipal();

        Produto novoProduto = novoProdutoRequest.toModel(entityManager, usuarioAutenticado);
        entityManager.persist(novoProduto);

        return novoProduto.toString();

    }
}
