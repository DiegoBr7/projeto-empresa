package br.com.empresa.service;


import br.com.empresa.dto.UsuarioCreateDTO;
import br.com.empresa.dto.UsuarioDTO;
import br.com.empresa.exception.RecursoNotFound;
import br.com.empresa.model.Usuario;
import br.com.empresa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(UsuarioCreateDTO dto){
       try {
           Usuario usuario = new Usuario();
           usuario.setNome(dto.getNome());
           usuario.setEmail(dto.getEmail());
           usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
           usuario.setCargo(dto.getCargo());

           return usuarioRepository.save(usuario);
       }catch (Exception e){
           throw new RuntimeException
                   ("Não foi possivel inserir usuário");
       }
    }

    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNotFound("Usuário não encontrado com id " + id));
    }

    public Usuario alterarUsuarioPorId(Usuario usuarioAtualizado , Long id){
            Usuario usuarioExistente = buscarUsuarioPorId(id);

            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setSenha(usuarioAtualizado.getSenha());
            usuarioExistente.setCargo(usuarioAtualizado.getCargo());

            return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id){
     Usuario usuario = buscarUsuarioPorId(id);
     usuarioRepository.delete(usuario);

    }


}
