package br.com.fiap.bookstoore.cp1.dto.customer;

import br.com.fiap.bookstoore.cp1.model.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerDetailsDTO(
        Long id,
        String name,
        String email,
        LocalDate birthdayDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {

    public CustomerDetailsDTO(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getBirthdayDate(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

}
