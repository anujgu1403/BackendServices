package com.retail.checkout.infrastructure.mapper;

import com.retail.checkout.domain.model.CartItemModel;
import com.retail.checkout.infrastructure.entity.CartItemEntity;
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
