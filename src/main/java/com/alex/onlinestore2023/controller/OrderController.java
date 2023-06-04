package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.OrderDto;
import com.alex.onlinestore2023.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getOrdersByUserId/{userId}")
    public ResponseEntity<OrderDto> getOrdersByUserId(@PathVariable("userId") Long userId) {
        List<OrderDto> orderDtoList = orderService.getOrdersByUserId(userId);
        return new ResponseEntity(orderDtoList, HttpStatus.OK);
    }
}
