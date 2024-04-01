package br.com.fiap.bookstoore.cp1.dto.customer;

import br.com.fiap.bookstoore.cp1.model.Customer;

import java.time.LocalDate;

public record CustomerDetailsDTO(
        Long id,
        String name,
        String email,
        LocalDate birthdayDate
) {

    public CustomerDetailsDTO(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getBirthdayDate()
        );
    }

}
