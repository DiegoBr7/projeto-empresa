package br.com.empresa.config;

import br.com.empresa.model.Usuario;
import br.com.empresa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException{

        Usuario u = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + email));

        String role = "ROLE_" + (u.getCargo() == null ? "ANALISTA" : u.getCargo().toUpperCase());
        return  new org.springframework.security.core.userdetails.User(
                u.getEmail(),
                u.getSenha(),
                List.of(new SimpleGrantedAuthority(role))
        );
    }

}
