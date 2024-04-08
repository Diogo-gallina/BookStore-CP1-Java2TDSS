package br.com.fiap.bookstoore.cp1.dto.shoppingCart;

import java.util.List;

public record CreateShoppingCartDTO(
        List<Long> bookIds
) {
}
