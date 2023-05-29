package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("addOrder/{userId}")
    public ResponseEntity addOrder(@PathVariable("userId") Long userId) {
        orderService.addOrder(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
