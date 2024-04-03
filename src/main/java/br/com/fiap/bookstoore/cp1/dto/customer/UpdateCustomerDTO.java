package br.com.fiap.bookstoore.cp1.dto.customer;

public record UpdateCustomerDTO(
        String name,
        String email,
        String password
        ) {

}
