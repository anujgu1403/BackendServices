package com.retail.cart.application.service;

import com.retail.cart.application.model.Cart;
import com.retail.cart.application.model.CartItem;

import java.util.List;

public interface CartService {
    Cart getUserCart(Long userId);
    int getCartItemCount(Long userId);
    List<CartItem> getCartItems(Long cartId);
    Cart getCart(Long cartId);
    Cart addItem(Cart cart);
    int deleteItem(Long cartId, Long itemId);
    boolean makeInactive(Long cartId);
    int updateQuantity(Long cartId, Long cartItemId, int quantity);
}