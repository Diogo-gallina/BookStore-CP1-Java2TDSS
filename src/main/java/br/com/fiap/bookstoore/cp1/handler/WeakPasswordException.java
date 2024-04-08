package br.com.fiap.bookstoore.cp1.handler;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException() {
        super("Weak password, password must contain at least 8 characters, an uppercase letter, a lowercase letter and a special character");
    }
}
