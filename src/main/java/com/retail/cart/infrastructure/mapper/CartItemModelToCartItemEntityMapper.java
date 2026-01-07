package com.retail.cart.infrastructure.mapper;

import com.retail.cart.domain.model.CartItemModel;
import com.retail.cart.infrastructure.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartItemModelToCartItemEntityMapper {
    public CartItemEntity apply(CartItemModel cartItemModel) {
        return CartItemEntity.builder()
                .id(cartItemModel.getCartItemId())
                .itemId(cartItemModel.getItemNumber())
                .createdDate(LocalDateTime.now())
                .quantity(cartItemModel.getQuantity())
                .unitPrice(cartItemModel.getUnitPrice())
                .build();
    }
}
