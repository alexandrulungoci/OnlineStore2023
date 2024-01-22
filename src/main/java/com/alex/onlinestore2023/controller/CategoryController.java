package com.alex.onlinestore2023.controller;

import com.alex.onlinestore2023.dto.CategoryDto;
import com.alex.onlinestore2023.dto.ProductDto;
import com.alex.onlinestore2023.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("addCategory")
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return new ResponseEntity(categoryDto, HttpStatus.OK);
    }

    @GetMapping("getCategory/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.getCategory(id);
        return new ResponseEntity(categoryDto, HttpStatus.OK);

    }

    @GetMapping("getCategories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        return new ResponseEntity(categoryDtoList, HttpStatus.OK);
    }

    @PutMapping("updateCategory")
    public ResponseEntity updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("deleteCategory/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
