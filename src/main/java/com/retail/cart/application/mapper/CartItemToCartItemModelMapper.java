package com.retail.checkout.application.mapper;

import com.retail.checkout.application.model.CartItem;
import com.retail.checkout.domain.model.CartItemModel;
import org.springframework.stereotype.Component;

@Component
public class CartItemToCartItemModelMapper {

    public CartItemModel apply(CartItem cartItem){
        return CartItemModel.builder()
                .cartId(cartItem.getCartId())
                .cartItemId(cartItem.getCartItemId())
                .itemNumber(cartItem.getItemNumber())
                .quantity(cartItem.getQuantity())
                .createdDate(cartItem.getCreatedDate())
                .unitPrice(cartItem.getUnitPrice())
                .build();
    }
}
