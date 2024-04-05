package br.com.fiap.bookstoore.cp1.dto.adress;

import br.com.fiap.bookstoore.cp1.model.Adress;
import br.com.fiap.bookstoore.cp1.model.Customer;

import java.time.LocalDateTime;

public record AdressDetailsDTO(
        Long id,
        Long customerId,
        String cep,
        String street,
        Integer adressNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public AdressDetailsDTO(Adress adress){
        this(
                adress.getId(),
                adress.getCustomer().getId(),
                adress.getCep(),
                adress.getStreet(),
                adress.getAdressNumber(),
                adress.getCreatedAt(),
                adress.getUpdatedAt()
        );
    }

}
