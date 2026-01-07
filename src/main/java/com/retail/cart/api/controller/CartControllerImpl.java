package com.retail.checkout.api.controller;

import com.retail.checkout.application.model.Cart;
import com.retail.checkout.application.model.CartItem;
import com.retail.checkout.application.service.CartService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartControllerImpl implements CartController {

    @Autowired
    private CartService service;

    public CartControllerImpl(CartService service) { this.service = service; }

    public ResponseEntity<Cart> addItem(@RequestBody Cart cart) {
        return ResponseEntity.ok(service.addItem(cart));
    }

    public ResponseEntity<Integer> deleteItem(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        return ResponseEntity.ok(service.deleteItem(cartId, cartItemId));
    }

    public ResponseEntity<Cart> getCartByCartId(@PathVariable Long cartId) {
        return ResponseEntity.ok(service.getCart(cartId));
    }

    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserCart(userId));
    }

    public ResponseEntity<Integer> updateQuantity(@PathVariable Long cartId,
                                                  @PathVariable Long cartItemId,
                                                  @RequestParam int delta) {
        return ResponseEntity.ok(service.updateQuantity(cartId, cartItemId, delta));
    }

    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        return ResponseEntity.ok(service.getCartItems(cartId));
    }
}