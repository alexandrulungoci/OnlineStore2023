package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getProduct/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto productDto = productService.getProduct(id);
        return new ResponseEntity(productDto, HttpStatus.OK);

    }

    @GetMapping("getProducts")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productList = productService.getAllProducts();
        return new ResponseEntity(productList, HttpStatus.OK);
    }

    @PutMapping("updateProduct")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
