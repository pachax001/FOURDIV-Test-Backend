package com.example.demo.Services;

import com.example.demo.Models.Category;
import com.example.demo.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Category updateCategory(UUID id,Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
