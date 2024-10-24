package com.example.demo.DTO;

import com.example.demo.Models.Product;

import java.util.UUID;

public class ProductDTO {
    private UUID id;
    private String name;
    private String code;
    private UUID categoryId;  // Only the ID will be passed from the frontend
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public ProductDTO() {}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.code = product.getCode();
        this.categoryId = product.getCategory().getId();  // Extract categoryId
        this.categoryName = product.getCategory().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}

