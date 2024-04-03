package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.book.BookDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.book.CreateBookDTO;
import br.com.fiap.bookstoore.cp1.model.Book;
import br.com.fiap.bookstoore.cp1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<BookDetailsDTO> create(
        @RequestBody CreateBookDTO bookDTO,
        UriComponentsBuilder uriBuilder
    ) {
        var book = bookService.create(bookDTO);
        var url = uriBuilder.path("books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(url).body(new BookDetailsDTO(book));
    }

}
