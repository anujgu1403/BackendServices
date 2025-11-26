package com.retail.cart.application.mapper;

import com.retail.cart.application.model.CartItem;
import com.retail.cart.domain.model.CartItemModel;
import org.springframework.stereotype.Component;

@Component
public class CartItemModelToCartItemMapper {

    public CartItem apply(CartItemModel cartItemModel){

        return CartItem.builder()
                .cartId(cartItemModel.getCartId())
                .cartItemId(cartItemModel.getCartItemId())
                .itemNumber(cartItemModel.getItemNumber())
                .createdDate(cartItemModel.getCreatedDate())
                .unitPrice(cartItemModel.getUnitPrice())
                .quantity(cartItemModel.getQuantity())
                .build();

    }
}
