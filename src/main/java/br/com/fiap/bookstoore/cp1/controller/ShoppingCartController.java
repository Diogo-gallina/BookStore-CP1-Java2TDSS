package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.shoppingCart.CreateShoppingCartDTO;
import br.com.fiap.bookstoore.cp1.dto.shoppingCart.ShoppingCartDetailsDTO;
import br.com.fiap.bookstoore.cp1.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("customer/{customer_id}")
    public ResponseEntity<ShoppingCartDetailsDTO> create(
            @PathVariable("customer_id") Long customerId,
            @RequestBody CreateShoppingCartDTO shoppingCartDTO,
            UriComponentsBuilder uri
    ){
        var shoppingCart = shoppingCartService.create(customerId, shoppingCartDTO);
        var url = uri.path("shoppingcarts/customer/{customer_id}")
                .buildAndExpand(customerId).toUri();

        return ResponseEntity.created(url).body(new ShoppingCartDetailsDTO(shoppingCart));
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDetailsDTO>> findAll(
            Pageable pageable
    ){
        var shoppingCartList = shoppingCartService.getAll(pageable);
        return ResponseEntity.ok(shoppingCartList);
    }

    @GetMapping("{shoppingcart_id}")
    public ResponseEntity<ShoppingCartDetailsDTO> findOne(
        @PathVariable("shoppingcart_id") Long shoppingCartId
    ){
        var shoppingCart = shoppingCartService.getOne(shoppingCartId);
        return ResponseEntity.ok(shoppingCart);
    }

    @DeleteMapping("{shoppingcart_id}")
    public ResponseEntity<Void> delete(
            @PathVariable("shoppingcart_id") Long shoppingCartId
    ){
        shoppingCartService.delete(shoppingCartId);
        return ResponseEntity.noContent().build();
    }

}
