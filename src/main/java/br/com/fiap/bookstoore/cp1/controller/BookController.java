package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.book.BookDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.book.CreateBookDTO;
import br.com.fiap.bookstoore.cp1.dto.book.UpdateBookDTO;
import br.com.fiap.bookstoore.cp1.model.Book;
import br.com.fiap.bookstoore.cp1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.ResourceBundle;

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

    @GetMapping
    public ResponseEntity<List<BookDetailsDTO>> findAll(Pageable pageable) {
        var bookList = bookService.getAll(pageable);

        return ResponseEntity.ok(bookList);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDetailsDTO> findAll(@PathVariable("id") Long id) {
        var bookList = bookService.getOne(id);

        return ResponseEntity.ok(bookList);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDetailsDTO> update(@PathVariable("id") Long id, @RequestBody UpdateBookDTO bookDTO) {
        var bookList = bookService.update(id, bookDTO);

        return ResponseEntity.ok(bookList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
