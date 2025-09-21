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

@Column(name = "preco_produto")
private BigDecimal precoProduto;


private Integer quantidade;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "data_criacao")
private Date dataCriacao;

@ManyToOne
@JoinColumn(name = "usuario_id")
private Usuario usuario;


@PrePersist
    public void prePersist(){
    this.dataCriacao = new Date();
}
}
