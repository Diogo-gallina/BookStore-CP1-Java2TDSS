package br.com.fiap.bookstoore.cp1.dto.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateCustomerDTO (
    String name,
    String email,
    String password,
    LocalDate birthdayDate
){
}
