package br.com.fiap.bookstoore.cp1.dto.shoppingCart;

import br.com.fiap.bookstoore.cp1.dto.assessment.AssessmentDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.book.BookDetailsDTO;
import br.com.fiap.bookstoore.cp1.model.Book;
import br.com.fiap.bookstoore.cp1.model.ShoppingCart;

import java.util.List;
import java.util.Set;

public record ShoppingCartDetailsDTO(
        Long shoppingCartId,
        Long customerId,
        List<BookDetailsDTO> books,
        Double amount
) {
    public ShoppingCartDetailsDTO(ShoppingCart shoppingCart){
        this(
                shoppingCart.getId(),
                shoppingCart.getCustomer().getId(),
                shoppingCart.getBooks().stream().map(BookDetailsDTO::new).toList(),
                shoppingCart.getAmount()
        );
    }
}
