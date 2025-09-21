package br.com.empresa.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class MessageErro {

    private LocalDateTime dataHora ;

    private String mensagem;

    private String detalhes;

    public MessageErro(LocalDateTime dataHora, String mensagem, String detalhes) {
        this.dataHora = dataHora;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }


}
