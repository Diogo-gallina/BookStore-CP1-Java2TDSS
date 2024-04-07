package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.model.ShoppingCart;
import br.com.fiap.bookstoore.cp1.repository.BookRepository;
import br.com.fiap.bookstoore.cp1.repository.CustomerRepository;
import br.com.fiap.bookstoore.cp1.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookRepository bookRepository;

    @Transactional
    public ShoppingCart create(
            Long customerId,

    ){
        var shoppingCart =

                shoppingCartRepository.save(shoppingCart);
    }

}
