package com.example.demo.Services;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }


    public ProductDTO getProductById(UUID id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            return new ProductDTO(productOpt.get());
        } else {
            throw new RuntimeException("Product not found");
        }
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, Product productDetails) {
        // Fetch the existing product by its ID
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update product fields
        existingProduct.setName(productDetails.getName());
        existingProduct.setCode(productDetails.getCode());

        // Fetch the category and set it if it's provided
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            Category category = categoryRepository.findById(productDetails.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category); // Set the updated category
        }

        // Save the updated product and return it
        return productRepository.save(existingProduct);
    }
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
