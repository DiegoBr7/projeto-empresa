package br.com.empresa.controller;

import br.com.empresa.dto.ProdutoDTO;
import br.com.empresa.model.Produto;
import br.com.empresa.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ApiProdutoController {

    @Autowired
    private ProdutoService produtoService ;

    @PreAuthorize("hasAnyRole('ANALISTA','SUPERVISOR')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoDTO> buscarTodos(){
        return
                produtoService.buscarTodosProdutos()
                        .stream()
                        .map(this::toDTO)
                        .toList();
    }

    @PreAuthorize("hasAnyRole('ANALISTA' , 'SUPERVISOR')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO buscarPorId(@PathVariable Long id){
        Produto produto = produtoService.buscarProdutoPorId(id);
        return toDTO(produto) ;
    }

    @PreAuthorize("hasRole('SUPERVISOR')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO alterarPorId(@PathVariable Long id , @RequestBody Produto produtoAtualizado){
        Produto produto = produtoService.alterarProdutoPorId(produtoAtualizado , id);
        return toDTO(produto) ;
    }

    @PreAuthorize("hasAnyRole('ANALISTA' , 'SUPERVISOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO salvarProduto (@RequestBody ProdutoDTO produtoDTO){
        Produto produto = produtoService.salvarProduto(produtoDTO);
        return toDTO(produto);
    }

    @PreAuthorize("hasRole('SUPERVISOR')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
    }

    private ProdutoDTO toDTO(Produto produto){
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPrecoProduto(produto.getPrecoProduto());
        dto.setQuantidade(produto.getQuantidade());
        dto.setDataCriacao(produto.getDataCriacao());
        dto.setUsuarioId(produto.getUsuario() != null ? produto.getUsuario().getId() : null);
        return dto;
    }

}
