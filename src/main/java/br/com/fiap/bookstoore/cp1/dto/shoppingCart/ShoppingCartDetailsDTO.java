package br.com.fiap.bookstoore.cp1.dto.shoppingCart;

import br.com.fiap.bookstoore.cp1.model.Book;
import br.com.fiap.bookstoore.cp1.model.ShoppingCart;

import java.util.List;

public record ShoppingCartDetailsDTO(
        Long shoppingCartId,
        Long customerId,
        List<Book> items,
        Double amount
) {
    public ShoppingCartDetailsDTO(ShoppingCart shoppingCart){
        this(
                shoppingCart.getId(),
                shoppingCart.getCustomer().getId(),
                shoppingCart.getItems(),
                shoppingCart.getAmount()
        );
    }
}
