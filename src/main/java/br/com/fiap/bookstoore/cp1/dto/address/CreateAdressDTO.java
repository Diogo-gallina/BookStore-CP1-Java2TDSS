package br.com.fiap.bookstoore.cp1.dto.address;

public record CreateAdressDTO(
        String cep,
        String street,
        Integer adressNumber
) {
}
