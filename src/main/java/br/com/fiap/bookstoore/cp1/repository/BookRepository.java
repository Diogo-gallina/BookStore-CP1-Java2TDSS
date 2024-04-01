package br.com.fiap.bookstoore.cp1.repository;

import br.com.fiap.bookstoore.cp1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
