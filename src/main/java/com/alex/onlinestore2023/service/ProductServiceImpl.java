package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.CategoryModel;
import com.alex.onlinestore2023.Model.ProductModel;
import com.alex.onlinestore2023.dto.CategoryDto;
import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.repository.CategoryRepository;
import com.alex.onlinestore2023.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void addProduct(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setId(productDto.getId());
        CategoryDto categoryDto = productDto.getCategory();

        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryDto.getId());
        if (categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();

            productModel.setCategory(categoryModel);
        }

        productModel.setProductName(productDto.getProductName());
        productModel.setPrice(productDto.getPrice());
      //  productModel.setThumbnail(productDto.getThumbnail());
        productRepository.save(productModel);
    }

    @Override
    public ProductDto getProduct(Long id) {
        Optional<ProductModel> foundProductModel = productRepository.findById(id);
        if (foundProductModel.isPresent()){
            ProductModel productModel = foundProductModel.get();
            ProductDto productDto = new ProductDto();

            productDto.setId(productModel.getId());
            productDto.setProductName(productModel.getProductName());
            productDto.setPrice(productModel.getPrice());
     //       productDto.setThumbnail(productModel.getThumbnail());

            CategoryModel categoryModel = productModel.getCategory();
            CategoryDto categoryDto = new CategoryDto();
            if (categoryModel != null){
                categoryDto.setId(categoryModel.getId());
                categoryDto.setCategoryName(categoryModel.getCategoryName());
                productDto.setCategory(categoryDto);
            }

            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        for (ProductModel productModel: productModelList){
            ProductDto productDto = new ProductDto();
            productDto.setId(productModel.getId());
            productDto.setProductName(productModel.getProductName());
            productDto.setPrice(productModel.getPrice());
       //     productDto.setThumbnail(productModel.getThumbnail());
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(productModel.getCategory().getId());
                categoryDto.setCategoryName(productModel.getCategory().getCategoryName());
            productDto.setCategory(categoryDto);
            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long id) {
        List<ProductDto> productDtoList = new ArrayList<>();
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(id);
        if (categoryModelOptional.isPresent()) {
            CategoryModel categoryModelFound = categoryModelOptional.get();



        List<ProductModel> productModelList = productRepository.findByCategory(categoryModelFound);

        for(ProductModel productModel : productModelList){
            ProductDto productDto = new ProductDto();
            productDto.setId(productModel.getId());
            productDto.setProductName(productModel.getProductName());
            productDto.setPrice(productModel.getPrice());
      //      productDto.setThumbnail(productModel.getThumbnail());
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryModelFound.getId());
            categoryDto.setCategoryName(categoryModelFound.getCategoryName());
            productDto.setCategory(categoryDto);
            productDtoList.add(productDto);
        }
    }
       return productDtoList;
    }

    @Override

    public void updateProduct(ProductDto productDto) {
        Optional<ProductModel> productModelOptional = productRepository.findById(productDto.getId());
        if (productModelOptional.isPresent()){
            ProductModel productModel = productModelOptional.get();

            long categoryId = productDto.getCategory().getId();
            Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryId);
            if (categoryModelOptional.isPresent()){
                CategoryModel categoryModel = categoryModelOptional.get();
                productModel.setCategory(categoryModel);
            }
            productModel.setProductName(productDto.getProductName());
            productModel.setPrice(productDto.getPrice());
     //       productModel.setThumbnail(productDto.getThumbnail());

            productRepository.save(productModel);
        }

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
