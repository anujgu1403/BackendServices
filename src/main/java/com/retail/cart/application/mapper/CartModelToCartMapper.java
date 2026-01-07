package com.retail.checkout.application.mapper;

import com.retail.checkout.application.model.Cart;
import com.retail.checkout.domain.model.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartModelToCartMapper {

    @Autowired
    CartItemModelToCartItemMapper cartItemModelToCartItemMapper;

    public Cart apply(CartModel cartModel) {

        return Cart.builder()
                .cartId(cartModel.getCartId())
                .userId(cartModel.getUserId())
                .cartItems(cartModel.getCartItemModels().stream()
                        .map(cartItemModel -> cartItemModelToCartItemMapper.apply(cartItemModel))
                        .collect(Collectors.toList()))
                .isActive(cartModel.isActive())
                .createdDate(cartModel.getCreatedDate())
                .build();

    }
}
