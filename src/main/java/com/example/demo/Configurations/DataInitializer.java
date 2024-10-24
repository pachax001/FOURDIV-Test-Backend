package com.example.demo.Configurations;

import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class DataInitializer {

    @Bean
    CommandLineRunner initData(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            // Create some sample categories
            Category electronics = new Category();
            electronics.setName("Electronics");
            electronics.setCode("ELEC");

            Category groceries = new Category();
            groceries.setName("Groceries");
            groceries.setCode("GROC");

            Category books = new Category();
            books.setName("Books");
            books.setCode("BOOK");

            // Save categories to the database
            List<Category> categories = Arrays.asList(electronics, groceries, books);
            categoryRepository.saveAll(categories);

            // Create some sample products for each category
            Product laptop = new Product();
            laptop.setName("Laptop");
            laptop.setCode("LPT001");
            laptop.setCategory(electronics);

            Product smartphone = new Product();
            smartphone.setName("Smartphone");
            smartphone.setCode("SPH001");
            smartphone.setCategory(electronics);

            Product bread = new Product();
            bread.setName("Bread");
            bread.setCode("BRD001");
            bread.setCategory(groceries);

            Product milk = new Product();
            milk.setName("Milk");
            milk.setCode("MLK001");
            milk.setCategory(groceries);

            Product novel = new Product();
            novel.setName("Novel");
            novel.setCode("NOV001");
            novel.setCategory(books);

            // Save products to the database
            productRepository.saveAll(Arrays.asList(laptop, smartphone, bread, milk, novel));

            System.out.println("Sample data inserted into the database.");
        };
    }
}
