package br.com.empresa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {
    private String nome;
    private String email;
    private String senha;
    private String cargo;
}
