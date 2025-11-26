package com.retail.cart.api.controller;

import com.retail.cart.application.model.Cart;
import com.retail.cart.application.model.CartItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CartController {
    @PostMapping("add")
    ResponseEntity<Cart> addItem(@RequestBody Cart cart);

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    ResponseEntity<Integer> deleteItem(@PathVariable Long cartId, @PathVariable Long cartItemId);

    @GetMapping("/{cartId}")
    ResponseEntity<Cart> getCartByCartId(@PathVariable Long cartId);

    @GetMapping("/user/{userId}")
    ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId);

    @PutMapping("/{cartId}/items/{cartItemId}/quantity")
    ResponseEntity<Integer> updateQuantity(@PathVariable Long cartId,
                                           @PathVariable Long cartItemId,
                                           @RequestParam int delta);

    @GetMapping("/{cartId}/items")
    ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId);
}
