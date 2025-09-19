package br.com.empresa.repository;

import br.com.empresa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
}
