package br.com.fiap.bookstoore.cp1.dto.adress;

public record UpdateAdressDTO(
        String cep,
        String street,
        Integer adressNumber
) {
}
