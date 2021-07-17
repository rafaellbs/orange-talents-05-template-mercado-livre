package br.com.zupacademy.rafael.treinomercadolivre.novaopniaoproduto;

import br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastroOpiniaoProdutoController {

    @Autowired
    private EntityManager manager;

    @PostMapping("/produtos/{idProduto}/opinioes")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaOpiniaoRequest request,
                                       @PathVariable Long idProduto, @AuthenticationPrincipal Usuario usuario) {

        Produto produto = obterProdutoPorId(idProduto);
        Opiniao novaOpiniao = request.toModel(produto, usuario);

        manager.persist(novaOpiniao);

        return ResponseEntity.ok().build();
    }

    private Produto obterProdutoPorId(Long idProduto) {
        Produto possivelProduto = manager.find(Produto.class, idProduto);

        if (possivelProduto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Este produto não existe");
        }

        return possivelProduto;
    }
}
