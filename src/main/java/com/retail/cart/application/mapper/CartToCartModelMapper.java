package com.retail.checkout.application.mapper;

import com.retail.checkout.application.model.Cart;
import com.retail.checkout.domain.model.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CartToCartModelMapper {

    @Autowired
    CartItemToCartItemModelMapper cartItemToCartItemModelMapper;

    public CartModel apply(Cart cart) {

        return CartModel.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .isActive(cart.isActive())
                .createdDate(cart.getCreatedDate())
                .cartItemModels(Optional.ofNullable(cart.getCartItems())
                        .map(cartItems ->
                                cartItems.stream()
                                        .map(cartItem -> cartItemToCartItemModelMapper.apply(cartItem))
                                        .toList())
                        .orElse(null))
                .build();

    }
}
