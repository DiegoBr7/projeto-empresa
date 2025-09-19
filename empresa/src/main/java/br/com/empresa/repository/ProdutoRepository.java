package br.com.empresa.repository;

import br.com.empresa.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto , Long> {
    void delete(Long id);

}
