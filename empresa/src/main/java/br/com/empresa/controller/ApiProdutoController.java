package br.com.empresa.controller;

import br.com.empresa.model.Produto;
import br.com.empresa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ApiProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> buscarTodos(){
        return produtoService.buscarTodosProdutos() ;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Produto> buscarPorId(@PathVariable Long id){
        return produtoService.buscarProdutoPorId(id) ;
    }

    @PostMapping("/enviar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto (@RequestBody Produto produto){
        return produtoService.salvarProduto(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
    }

}
