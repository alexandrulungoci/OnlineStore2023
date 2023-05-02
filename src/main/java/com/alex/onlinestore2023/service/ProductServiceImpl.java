package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.ProductModel;
import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void addProduct(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setId(productDto.getId());
        // CategoryDTO

        productModel.setPrice(productDto.getPrice());
        productModel.setProductName(productDto.getProductName());
        productRepository.save(productModel);
    }

    @Override
    public ProductDto getProduct(Long id) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @Override
    public void updateProduct(ProductDto productDto) {

    }

    @Override
    public void deleteProduct(Long id) {

    }
}
