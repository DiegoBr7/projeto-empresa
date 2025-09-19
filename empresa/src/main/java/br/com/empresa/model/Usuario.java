package br.com.empresa.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Usuario {

@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
private Long id ;

@NotBlank
private String nome ;

@Email
@NotBlank
private String email;

@NotBlank
private String senha;


private String cargo;

@OneToMany(mappedBy =  "usuario" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<Produto>();
}
