package br.com.zupacademy.rafael.treinomercadolivre.detalheproduto;

import br.com.zupacademy.rafael.treinomercadolivre.cadastroproduto.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/produtos/{id}")
    public ResponseEntity<DetalheProdutoResponse> detalhe(@PathVariable Long id) {
        Produto produto = manager.find(Produto.class, id);

        return ResponseEntity.ok(new DetalheProdutoResponse(produto));
    }
}
