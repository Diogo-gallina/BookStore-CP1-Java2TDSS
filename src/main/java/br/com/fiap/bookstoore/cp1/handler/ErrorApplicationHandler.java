package br.com.fiap.bookstoore.cp1.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorApplicationHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404(EntityNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({InvalidEmailException.class, WeakPasswordException.class})
    public ResponseEntity error400(){
        return ResponseEntity.badRequest().build();
    }

}
