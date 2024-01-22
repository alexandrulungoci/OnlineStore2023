package com.alex.onlinestore2023.repository;

import com.alex.onlinestore2023.Model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
