package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.dto.ProductDto;

import java.util.List;

public interface ProductService {

    void addProduct(ProductDto productDto);

    ProductDto getProduct(Long id);

    List<ProductDto> getAllProducts();

    void updateProduct(ProductDto productDto);

    void deleteProduct(Long id);

}
