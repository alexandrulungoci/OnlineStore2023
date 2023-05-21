package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping("addToCart/{userId}/{productId}")
    public ResponseEntity addToCart(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        cartService.addToCart(userId, productId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
