package com.example.demo.Controllers;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        // Find the Category by ID
        Optional<Category> categoryOpt = categoryRepository.findById(productDTO.getCategoryId());
        if (categoryOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();  // Handle case when category is not found
        }

        // Create and save the new Product
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(productDTO.getCode());
        product.setCategory(categoryOpt.get());  // Set the category object, not just the ID
        productService.createProduct(product);



        return ResponseEntity.ok(product);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody Product productDetails) {
        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}
