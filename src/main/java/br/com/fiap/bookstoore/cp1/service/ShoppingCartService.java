package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.dto.shoppingCart.ShoppingCartDetailsDTO;
import br.com.fiap.bookstoore.cp1.model.ShoppingCart;
import br.com.fiap.bookstoore.cp1.repository.BookRepository;
import br.com.fiap.bookstoore.cp1.repository.CustomerRepository;
import br.com.fiap.bookstoore.cp1.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookRepository bookRepository;

//    @Transactional
//    public ShoppingCart create(
//            Long customerId,
//
//    ){
//        var shoppingCart =
//
//                shoppingCartRepository.save(shoppingCart);
//    }

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

//    @Transactional
//    public ShoppingCartDetailsDTO update(
//            Long ShoppingCartId,
//            Long customerId
//    ){
//
//    }

    @Transactional
    public void delete(
            Long shoppingCartId
    ){
        shoppingCartRepository.deleteById(shoppingCartId);
    }



}
