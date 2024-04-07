package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.shoppingCart.ShoppingCartDetailsDTO;
import br.com.fiap.bookstoore.cp1.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoppingCarts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCartDetailsDTO>> findAll(
            Pageable pageable
    ){
        var shoppingCartList = shoppingCartService.getAll(pageable);
        return ResponseEntity.ok(shoppingCartList);
    }

    @GetMapping("{shoppingCart_id}")
    public ResponseEntity<ShoppingCartDetailsDTO> findOne(
        @PathVariable("shoppingCart_id") Long shoppingCartId
    ){
        var shoppingCart = shoppingCartService.getOne(shoppingCartId);
        return ResponseEntity.ok(shoppingCart);
    }

    @DeleteMapping("{shoppingCart_id}")
    public ResponseEntity<Void> delete(
            @PathVariable("shoppingCart_id") Long shoppingCartId
    ){
        shoppingCartService.delete(shoppingCartId);
        return ResponseEntity.noContent().build();
    }

}
