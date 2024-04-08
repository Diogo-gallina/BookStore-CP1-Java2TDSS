package br.com.fiap.bookstoore.cp1.dto.address;

public record UpdateAdressDTO(
        String cep,
        String street,
        Integer adressNumber
) {
}
