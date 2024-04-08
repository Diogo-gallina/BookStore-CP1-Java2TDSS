package br.com.fiap.bookstoore.cp1.dto.book;

import br.com.fiap.bookstoore.cp1.model.GenderTypes;

import java.math.BigDecimal;

public record UpdateBookDTO(
        String name,
        String author,
        GenderTypes gender,
        Double value
        ) {
}
