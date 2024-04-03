package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.dto.book.BookDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.book.CreateBookDTO;
import br.com.fiap.bookstoore.cp1.dto.book.UpdateBookDTO;
import br.com.fiap.bookstoore.cp1.model.Book;
import br.com.fiap.bookstoore.cp1.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public Book create(CreateBookDTO bookDTO) {
        var book = new Book(bookDTO);
        return bookRepository.save(book);
    }

    public List<BookDetailsDTO> getAll(Pageable pageable){
        var bookList = bookRepository.findAll(pageable)
                .stream().map(BookDetailsDTO::new).toList();
        return bookList;
    }

    public BookDetailsDTO getOne(Long id){
        var book = bookRepository.getReferenceById(id);
        return new BookDetailsDTO(book);
    }

    @Transactional
    public BookDetailsDTO update(Long id, UpdateBookDTO bookDTO){
        var book = bookRepository.getReferenceById(id);

        if(book.getName() != null)
            book.setName(bookDTO.name());

        if(book.getAuthor() != null)
            book.setAuthor(bookDTO.author());

        if(book.getGender() != null)
            book.setGender(bookDTO.gender());

        if(book.getValue() != null)
            book.setValue(bookDTO.value());

        bookRepository.save(book);

        return new BookDetailsDTO(book);

    }

    @Transactional
    public void delete(Long id){
        bookRepository.deleteById(id);
    }

}
