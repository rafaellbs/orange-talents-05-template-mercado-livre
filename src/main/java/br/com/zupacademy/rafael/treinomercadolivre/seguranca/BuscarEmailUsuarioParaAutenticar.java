package br.com.zupacademy.rafael.treinomercadolivre.seguranca;

import br.com.zupacademy.rafael.treinomercadolivre.novousuario.Usuario;
import br.com.zupacademy.rafael.treinomercadolivre.novousuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarEmailUsuarioParaAutenticar implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(userName);
        if (usuario.isPresent()){
            return usuario.get();
        }
      throw new UsernameNotFoundException("Dados inválidos");
    }
}
