package br.com.empresa.controller;

import br.com.empresa.dto.UsuarioCreateDTO;
import br.com.empresa.dto.UsuarioDTO;
import br.com.empresa.model.Usuario;
import br.com.empresa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioDTO> listarUsuario(){
        return usuarioService.listarUsuario()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @PostMapping("/enviar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO enviarUsuario(@RequestBody UsuarioCreateDTO usuarioDTO){
        Usuario usuario = usuarioService.salvarUsuario(usuarioDTO);
        return toDTO(usuario);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO buscarUsuarioPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return toDTO(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO alterarUsuarioPorId(@PathVariable Long id , @RequestBody Usuario usuarioAtualizado){
        Usuario usuario = usuarioService.alterarUsuarioPorId(usuarioAtualizado , id);
        return toDTO(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }

    private UsuarioDTO toDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCargo(usuario.getCargo());
        dto.setProdutosIds(
                usuario.getProdutos()
                        .stream()
                        .map(p -> p.getId())
                        .toList()
        );
        return dto;
    }

}
