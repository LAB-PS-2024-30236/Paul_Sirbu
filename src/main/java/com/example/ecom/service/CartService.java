package com.example.ecom.service;

import com.example.ecom.model.Cart;
import com.example.ecom.model.CartItem;
import com.example.ecom.model.LocalUser;
import com.example.ecom.model.Product;
import com.example.ecom.repository.CartItemRepository;
import com.example.ecom.repository.CartRepository;
import com.example.ecom.repository.LocalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private LocalUserRepository localUserRepository;

    public Cart createCart(Long userId) {
        LocalUser user = localUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    public void addToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productService.getProductById(productId).orElseThrow(() -> new RuntimeException("Product not found")); // Ensure product exists
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
    }

    public void deleteAllProductsFromCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().clear(); // Remove all cart items
        cartRepository.save(cart); // Save the updated cart
    }

    public void deleteProductFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Find the cart item by its ID and remove it from the cart
        cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));

        cartRepository.save(cart); // Save the updated cart
    }


    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
