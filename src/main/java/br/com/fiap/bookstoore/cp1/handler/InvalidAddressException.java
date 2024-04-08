package br.com.fiap.bookstoore.cp1.handler;

public class InvalidAddressException extends RuntimeException{
    public InvalidAddressException (){
        super("Invalid adress");
    }
}
