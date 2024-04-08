package br.com.fiap.bookstoore.cp1.handler;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException (){
        super("Invalid email");
    }
}
