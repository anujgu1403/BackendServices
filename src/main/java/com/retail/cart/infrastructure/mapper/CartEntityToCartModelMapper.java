package com.retail.checkout.infrastructure.mapper;

import com.retail.checkout.domain.model.CartModel;
import com.retail.checkout.infrastructure.entity.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartEntityToCartModelMapper {

    @Autowired
    CartItemEntityToCartItemModelMapper cartItemEntityToCartItemModelMapper;

    public CartModel apply(CartEntity cartEntity){
        return CartModel.builder()
                .cartId(cartEntity.getId())
                .userId(cartEntity.getUserId())
                .isActive(cartEntity.isActive())
                .createdDate(cartEntity.getCreatedDate())
                .cartItemModels(cartEntity.getCartItems().stream()
                        .map(cartItemEntity->cartItemEntityToCartItemModelMapper.apply(cartItemEntity))
                        .collect(Collectors.toList()))
                .build();

    }
}
