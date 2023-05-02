package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("addProduct")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
