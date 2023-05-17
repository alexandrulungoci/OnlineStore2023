package com.alex.onlinestore2023.service;



import com.alex.onlinestore2023.Model.CategoryModel;
import com.alex.onlinestore2023.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    void addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategories();

    void updateCategory(CategoryDto categoryDto);

    void deleteCategory(Long id);
}
