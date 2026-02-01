package com.retail.cart.application.mapper;

import com.retail.cart.application.model.CartItem;
import com.retail.cart.domain.model.CartItemModel;
import org.springframework.stereotype.Component;

@Component
public class CartItemModelToCartItemMapper {

    public CartItem apply(CartItemModel cartItemModel){

        return CartItem.builder()
                .cartItemId(cartItemModel.getCartItemId())
                .productId(cartItemModel.getProductId())
                .productName(cartItemModel.getProductName())
                .description(cartItemModel.getDescription())
                .imageUrl(cartItemModel.getImageUrl())
                .createdDate(cartItemModel.getCreatedDate())
                .unitPrice(cartItemModel.getUnitPrice())
                .quantity(cartItemModel.getQuantity())
                .build();

    }
}
