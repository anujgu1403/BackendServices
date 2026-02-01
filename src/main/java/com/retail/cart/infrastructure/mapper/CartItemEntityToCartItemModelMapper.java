package com.retail.cart.infrastructure.mapper;

import com.retail.cart.domain.model.CartItemModel;
import com.retail.cart.infrastructure.entity.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemEntityToCartItemModelMapper {
    public CartItemModel apply(CartItemEntity cartItemEntity){
       return CartItemModel.builder()
                .cartId(cartItemEntity.getCart().getId())
                .itemNumber(cartItemEntity.getItemId())
                .quantity(cartItemEntity.getQuantity())
                .createdDate(cartItemEntity.getCreatedDate())
                .unitPrice(cartItemEntity.getUnitPrice())
                .cartItemId(cartItemEntity.getId())
                .build();
    }
}
