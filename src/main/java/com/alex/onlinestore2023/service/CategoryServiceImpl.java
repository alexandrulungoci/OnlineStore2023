package com.alex.onlinestore2023.service;

import com.alex.onlinestore2023.Model.CategoryModel;

import com.alex.onlinestore2023.dto.CategoryDto;
import com.alex.onlinestore2023.repository.CategoryRepository;
import com.alex.onlinestore2023.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void addCategory(CategoryDto categoryDto) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryName(categoryDto.getCategoryName());

        categoryRepository.save(categoryModel);
    }


    @Override
    public com.alex.onlinestore2023.dto.CategoryDto getCategory(Long id) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(id);
        if (categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryModel.getId());
            categoryDto.setCategoryName(categoryModel.getCategoryName());
            return categoryDto;
        }
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryModel> categoryModelList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(CategoryModel categoryModel: categoryModelList){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryModel.getId());
            categoryDto.setCategoryName(categoryModel.getCategoryName());
            categoryDtoList.add(categoryDto);
        }

        return categoryDtoList;
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryDto.getId());
        if (categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();
            categoryModel.setCategoryName(categoryDto.getCategoryName());
            categoryRepository.save(categoryModel);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);

    }
}
