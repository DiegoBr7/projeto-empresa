package br.com.empresa.service;

import br.com.empresa.model.Produto;
import br.com.empresa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

@Autowired
private ProdutoRepository produtoRepository;

public Produto salvarProduto(Produto produto){
    return produtoRepository.save(produto);
}

public List<Produto> buscarTodosProdutos(){
    return produtoRepository.findAll();
}

public Optional<Produto> buscarProdutoPorId(Long id){
    return produtoRepository.findById(id);
}

public void deletarProduto(Long id){
    produtoRepository.delete(id);
}

}
