package br.com.fiap.bookstoore.cp1.repository;

import br.com.fiap.bookstoore.cp1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
