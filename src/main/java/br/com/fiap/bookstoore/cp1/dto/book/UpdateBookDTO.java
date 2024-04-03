package br.com.fiap.bookstoore.cp1.dto.book;

import br.com.fiap.bookstoore.cp1.model.GenderTypes;

public record UpdateBookDTO(
        String name,
        String author,
        GenderTypes gender,
        Double value
        ) {
}
