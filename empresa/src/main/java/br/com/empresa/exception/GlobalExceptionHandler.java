package br.com.empresa.exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    MessageSource messageSource;
    @Autowired
    LocaleResolver localeResolver;
    @ExceptionHandler(RecursoNotFound.class)
    public ResponseEntity<MessageErro> recursoNotFound(RecursoNotFound e , HttpServletRequest request){
        log.error("Erro RecursoNotFound: {}",e.getMessage());

        Locale local = localeResolver.resolveLocale(request);

        String msgLang = messageSource.getMessage("recurso_notfound" , null , local);

        MessageErro msg = new MessageErro(
                LocalDateTime.now(),
                msgLang
                ,request.getContextPath()
        );

        return new ResponseEntity<>
                (msg , HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?>
            geralRunTimeException(RuntimeException e , WebRequest request){
        log.error("Erro genérico: {}" , e.getMessage() , e);

        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
