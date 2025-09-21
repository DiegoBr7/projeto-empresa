package br.com.empresa.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String token;
    public LoginResponseDTO(String token){
        this.token =token;
    }
    public String getToken(){
        return token;
    }
}
