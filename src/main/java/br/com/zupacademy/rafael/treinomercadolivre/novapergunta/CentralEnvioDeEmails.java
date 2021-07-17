package br.com.zupacademy.rafael.treinomercadolivre.novapergunta;

import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentralEnvioDeEmails {

    @Autowired
    private EnvioDeEmailFake envioDeEmailFake;

    public void enviaEmailNovaPergunta(Pergunta pergunta) {
        Usuario remetente = pergunta.getUsuario();
        Usuario destinatario = pergunta.donoDoProduto();
        String tituloPergunta = pergunta.getTitulo();
        String nomeDoProduto =  pergunta.nomeDoProduto();

        envioDeEmailFake.envia(tituloPergunta, nomeDoProduto, remetente, destinatario);
    }
}
