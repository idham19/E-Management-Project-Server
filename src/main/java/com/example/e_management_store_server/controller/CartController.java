package com.example.e_management_store_server.controller;

import com.example.e_management_store_server.model.Cart;
import com.example.e_management_store_server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart postCart(@RequestBody Cart cart) {
        return cartService.postCart(cart);
    }
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.findAllCart();
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCartById(@PathVariable Long id) {
        return cartService.findCartById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable Long id) {
        cartService.deleteCartById(id);
    }
}
