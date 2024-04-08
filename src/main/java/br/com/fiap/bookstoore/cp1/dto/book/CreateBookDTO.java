package br.com.fiap.bookstoore.cp1.dto.book;

import br.com.fiap.bookstoore.cp1.model.GenderTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateBookDTO(
        String name,
        String isbn,
        String author,
        GenderTypes gender,
        Double value

) {
}
