package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.dto.shoppingCart.CreateShoppingCartDTO;
import br.com.fiap.bookstoore.cp1.dto.shoppingCart.ShoppingCartDetailsDTO;
import br.com.fiap.bookstoore.cp1.model.Book;
import br.com.fiap.bookstoore.cp1.model.Customer;
import br.com.fiap.bookstoore.cp1.model.ShoppingCart;
import br.com.fiap.bookstoore.cp1.repository.BookRepository;
import br.com.fiap.bookstoore.cp1.repository.CustomerRepository;
import br.com.fiap.bookstoore.cp1.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            CreateShoppingCartDTO shoppingCartDTO
    ){
        Customer customer = customerRepository.getReferenceById(customerId);

        Set<Book> books = new HashSet<>();

        for (Long bookId : shoppingCartDTO.bookIds()) {
            Book book = bookRepository.getReferenceById(bookId);
            books.add(book);
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customer);
        shoppingCart.setBooks(books);

        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCartDetailsDTO getOne(
        Long shoppingCartId
    ){
        var shoppingCart = shoppingCartRepository.getReferenceById(shoppingCartId);
        return new ShoppingCartDetailsDTO(shoppingCart);
    }

    public List<ShoppingCartDetailsDTO> getAll(
            Pageable pageable
    ){
        var shoppingCartList = shoppingCartRepository.findAll(pageable)
                .stream().map(ShoppingCartDetailsDTO::new).toList();
        return shoppingCartList;
    }

    @Transactional
    public void delete(
            Long shoppingCartId
    ){
        shoppingCartRepository.deleteById(shoppingCartId);
    }



}
