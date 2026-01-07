package com.retail.cart.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class CartModel {
    private Long cartId;
    private Long userId;
    private LocalDateTime createdDate;
    private boolean isActive;
    private List<CartItemModel> cartItemModels;
    private CartSummaryModel cartSummaryModel;
}