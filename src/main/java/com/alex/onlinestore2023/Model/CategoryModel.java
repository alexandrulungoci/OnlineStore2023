package com.alex.onlinestore2023.Model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoryName;

    @OneToMany (mappedBy = "parentCategory")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<CategoryModel> subcategory = new ArrayList<>();

    @ManyToOne
    private CategoryModel parentCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryModel> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<CategoryModel> subcategory) {
        this.subcategory = subcategory;
    }

    public CategoryModel getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryModel parentCategory) {
        this.parentCategory = parentCategory;
    }
}