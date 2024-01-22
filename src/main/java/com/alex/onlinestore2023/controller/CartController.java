package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.CartDto;
import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("findByUserModel_Id/{userId}")
    public ResponseEntity<CartDto> findByUserModel_Id(@PathVariable("userId") Long userId) {
        CartDto cartDto = cartService.findByUserModel_Id(userId);
        return new ResponseEntity(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("deleteOrderLineFromCart/{userId}/{orderLineId}")
    public ResponseEntity deleteOrderLineFromCart(@PathVariable Long userId,@PathVariable Long orderLineId) {
        cartService.deleteOrderLineFromCart(userId, orderLineId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
