package com.alex.onlinestore2023.dto;

import com.alex.onlinestore2023.Model.CategoryModel;

import javax.persistence.ManyToOne;

public class ProductDto {

    private long id;

    private String productName;

    private CategoryModel category;

    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
