package com.retail.checkout.infrastructure.mapper;

import com.retail.checkout.domain.model.CartModel;
import com.retail.checkout.infrastructure.entity.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CartModelToCartEntityMapper {

    @Autowired
    CartItemModelToCartItemEntityMapper cartItemModelToCartItemEntityMapper;

    public CartEntity apply(CartModel cartModel) {
        return CartEntity.builder()
                .id(cartModel.getCartId())
                .userId(cartModel.getUserId())
                .createdDate(LocalDateTime.now())
                .cartItems(Optional.ofNullable(cartModel.getCartItemModels())
                        .map(cartItemModels ->
                                cartItemModels.stream()
                                        .map(cartItemModel -> cartItemModelToCartItemEntityMapper.apply(cartItemModel))
                                        .toList())
                        .orElse(null))
                .build();

    }
}
