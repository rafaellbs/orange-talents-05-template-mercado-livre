package br.com.zupacademy.rafael.treinomercadolivre.novapergunta;

import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EnvioDeEmailFake {

    public void envia(String titulo, String nomeDoProduto, Usuario remetente, Usuario destinatario) {
        System.out.println("Olá " + destinatario.getUsername());
        System.out.println("Você tem uma nova pergunra no produto: " + nomeDoProduto);
        System.out.println("Do(a) consumidor(a) " + remetente.getUsername());
        System.out.println("Pergunta: " + titulo);
    }

}
