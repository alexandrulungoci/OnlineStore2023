package com.alex.onlinestore2023.repository;

import com.alex.onlinestore2023.Model.CategoryModel;
import com.alex.onlinestore2023.Model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {


    List<ProductModel> findByCategory(CategoryModel categoryModel);
}
