package com.retail.checkout.infrastructure.mapper;

import com.retail.checkout.domain.model.CartItemModel;
import com.retail.checkout.infrastructure.entity.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemEntityToCartItemModelMapper {
    public CartItemModel apply(CartItemEntity cartItemEntity){
       return CartItemModel.builder()
                .cartId(cartItemEntity.getId())
                .itemNumber(cartItemEntity.getItemId())
                .quantity(cartItemEntity.getQuantity())
                .createdDate(cartItemEntity.getCreatedDate())
                .unitPrice(cartItemEntity.getUnitPrice())
                .cartItemId(cartItemEntity.getId())
                .build();
    }
}
