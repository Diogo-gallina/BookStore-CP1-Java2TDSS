package br.com.fiap.bookstoore.cp1.dto.book;

import br.com.fiap.bookstoore.cp1.model.Book;
import br.com.fiap.bookstoore.cp1.model.GenderTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookDetailsDTO(
        Long id,
        String name,
        String isbn,
        String author,
        GenderTypes gender,
        Double value,
        Double rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public BookDetailsDTO(Book book) {
        this(
                book.getId(),
                book.getName(),
                book.getIsbn(),
                book.getAuthor(),
                book.getGender(),
                book.getValue(),
                book.getRating(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

}
