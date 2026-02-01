package com.retail.cart.infrastructure.mapper;

import com.retail.cart.domain.model.CartItemModel;
import com.retail.cart.infrastructure.model.ProductServiceResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceResponseMapper {

    public CartItemModel apply(ProductServiceResponse productServiceResponse, CartItemModel requestedItem){
        return CartItemModel.builder()
                .productId(productServiceResponse.getProductId())
                .productName(productServiceResponse.getName())
                .description(productServiceResponse.getDescription())
                .imageUrl(productServiceResponse.getImageUrl())
                .unitPrice(productServiceResponse.getUnitPrice())
                .quantity(requestedItem.getQuantity())
                .build();
    }
}
