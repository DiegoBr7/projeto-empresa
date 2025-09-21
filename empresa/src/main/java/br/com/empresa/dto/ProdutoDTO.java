package br.com.empresa.dto;


import br.com.empresa.model.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProdutoDTO {

    private Long id ;
    private String nome;
    private String descricao;
    private BigDecimal precoProduto;
    private Integer quantidade;
    private Date dataCriacao;

    private Long usuarioId;



}
