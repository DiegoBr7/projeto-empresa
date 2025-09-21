package br.com.empresa.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO {

    private Long id ;
    private String nome ;
    private String email;
    private String cargo;


    private List<Long> produtosIds;

}
