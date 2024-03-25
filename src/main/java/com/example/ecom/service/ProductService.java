package com.example.ecom.service;

import com.example.ecom.model.Product;
import com.example.ecom.repository.LocalUserRepository;
import com.example.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocalUserRepository localUserRepository;

    public Product saveProduct(Product product, Long userId){
            if(localUserRepository.getById(userId).isAdmin()) {
                return productRepository.save(product);
        }
        else{
                throw new RuntimeException("You don't have access to this functionality");
            }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId, Long userId) {
        if (localUserRepository.getById(userId).isAdmin()) {
            return productRepository.findById(productId);
        } else {
            throw new RuntimeException("You don't have access to this functionality");

        }
    }

    public Product updateProduct(Long productId, Product updatedProduct, Long userId) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setShortDescription(updatedProduct.getShortDescription());
            existingProduct.setLongDescription(updatedProduct.getLongDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
                if(localUserRepository.getById(userId).isAdmin()) {
                    return productRepository.save(existingProduct);
            }
                else{
                    throw new RuntimeException("You don't have access to this functionality");
                }
        } else {
            // Handle product not found scenario
            return null;
        }
    }

    public void deleteProduct(Long productId, Long userId) {
        if(localUserRepository.getById(userId).isAdmin()) {
            productRepository.deleteById(productId);
        }
        else{
            throw new RuntimeException("You don't have access to this functionality");
        }
    }
}
