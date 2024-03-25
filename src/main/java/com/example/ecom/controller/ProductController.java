package com.example.ecom.controller;

import com.example.ecom.model.Product;
import com.example.ecom.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/saveproduct/{userId}")
    public Product saveProduct(@RequestBody Product product, @PathVariable Long userId){
        return productService.saveProduct(product, userId);
    }

    @GetMapping("/getallproducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getproduct/{id}/{userId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id, @PathVariable Long userId) {
        Optional<Product> product = productService.getProductById(id, userId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateproduct/{id}/{userId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct, @PathVariable Long userId) {
        Product result = productService.updateProduct(id, updatedProduct, userId);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteproduct/{id}/{userId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @PathVariable Long userId) {
        productService.deleteProduct(id, userId);
        return ResponseEntity.noContent().build();
    }
}
