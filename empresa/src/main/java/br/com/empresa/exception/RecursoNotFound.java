package br.com.empresa.exception;

import java.io.Serial;

public class RecursoNotFound extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecursoNotFound(String mensagem){
        super(mensagem);
    }

}
