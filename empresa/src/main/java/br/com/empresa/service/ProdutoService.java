package br.com.empresa.service;

import br.com.empresa.dto.ProdutoDTO;
import br.com.empresa.exception.RecursoNotFound;
import br.com.empresa.model.Produto;
import br.com.empresa.model.Usuario;
import br.com.empresa.repository.ProdutoRepository;
import br.com.empresa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

@Autowired
private ProdutoRepository produtoRepository;

@Autowired
private UsuarioRepository usuarioRepository;

public Produto salvarProduto(ProdutoDTO dto){
    Produto produto =  toEntity(dto);


    //verificar se o usuárioId do DTO está associado ao produto

    if (dto.getUsuarioId() != null){
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() -> new RecursoNotFound("Usuario não encontrado com id" + dto));
produto.setUsuario(usuario);
    }
return produtoRepository.save(produto);
}

public List<Produto> buscarTodosProdutos(){
    return produtoRepository.findAll();
}


public List<Produto> buscarPorNome(String nome) {
  return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }


public Produto buscarProdutoPorId(Long id){

    return produtoRepository.findById(id)
            .orElseThrow(() -> new RecursoNotFound("Recurso não foi encontrado com id " + id));
}

    public Produto alterarProdutoPorId(Produto produtoAtualizado , Long id){
        Produto produtoExistente = buscarProdutoPorId(id);

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPrecoProduto(produtoAtualizado.getPrecoProduto());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());

        return produtoRepository.save(produtoExistente);
    }

public void deletarProduto(Long id){
    produtoRepository.deleteById(id);
}

private Produto toEntity (ProdutoDTO dto){
    Produto produto = new Produto();
    produto.setId(dto.getId());
    produto.setNome(dto.getNome());
    produto.setDescricao(dto.getDescricao());
    produto.setPrecoProduto(dto.getPrecoProduto());
    produto.setQuantidade(dto.getQuantidade());
    produto.setDataCriacao(dto.getDataCriacao());
    return produto;
    }


}
