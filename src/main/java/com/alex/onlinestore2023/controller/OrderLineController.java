package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.OrderLineDto;
import com.alex.onlinestore2023.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;



    @PutMapping("updateOrderLine/{id}")
    public ResponseEntity updateOrderLine(@RequestBody OrderLineDto orderLineDto, @PathVariable("id") Long id) {
        orderLineService.updateOrderLine(orderLineDto, id );
        return new ResponseEntity(HttpStatus.OK);
    }

}
