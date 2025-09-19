package br.com.empresa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Produto {

@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
private Long id ;

private String nome;
private String descricao;
private BigDecimal preco_Produto;
private Integer quantidade;
private Date data_Criacao;

@ManyToOne
@JoinColumn(name = "usuario_id")
private Usuario usuario;

}
