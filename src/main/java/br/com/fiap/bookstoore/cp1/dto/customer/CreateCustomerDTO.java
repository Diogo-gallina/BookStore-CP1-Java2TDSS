package br.com.fiap.bookstoore.cp1.dto.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateCustomerDTO (
//        @NotBlank(message = "Name is mandatory")
    String name,

//        @NotBlank(message = "Email is mandatory")
    String email,

//        @NotBlank(message = "Password is mandatory")
    String password,
    LocalDate birthdayDate
){
}
