package br.com.fiap.bookstoore.cp1.dto.adress;

import br.com.fiap.bookstoore.cp1.model.Customer;

public record CreateAdressDTO(
        String cep,
        String street,
        Integer adressNumber
) {
}
