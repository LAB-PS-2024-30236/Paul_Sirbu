package com.example.ecom.controller;

import com.example.ecom.model.Cart;
import com.example.ecom.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId) {
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.ok(cart);
    }
    @PostMapping("/add/{cartId}/{productId}/{quantity}")
    public ResponseEntity<Void> addToCart(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable int quantity) {
        cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    // Endpoint to delete all products from the cart
    @DeleteMapping("/clear/{cartId}")
    public ResponseEntity<Void> deleteAllProductsFromCart(@PathVariable Long cartId) {
        cartService.deleteAllProductsFromCart(cartId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to delete a product by ID from the cart
    @DeleteMapping("/{cartId}/{cartItemId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.deleteProductFromCart(cartId, cartItemId);
        return ResponseEntity.noContent().build();
    }
}
