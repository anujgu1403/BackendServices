package com.retail.cart.infrastructure.repository;

import com.retail.cart.domain.model.CartModel;
import com.retail.cart.domain.model.CartItemModel;

import java.util.List;

public interface CartRepository {
    CartModel addItem(CartModel cartModel);
    int deleteItem(Long cartId, Long itemId);
    CartModel getCart(Long cartId);
    int getCartItemCount(Long userId);
    List<CartItemModel> getCartItems(Long cartId);
    CartModel getUserCart(Long userId);
    boolean makeInactive(Long cartId);
    int updateQuantity(Long cartId, Long cartItemId, int quantity);
}