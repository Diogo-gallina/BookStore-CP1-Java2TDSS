package br.com.fiap.bookstoore.cp1.repository;

import br.com.fiap.bookstoore.cp1.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
