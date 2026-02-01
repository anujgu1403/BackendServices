package com.retail.cart.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemModel {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private String description;
    private String imageUrl;
    private BigDecimal unitPrice;
    private int quantity;
    private Long cartId;
    private LocalDateTime createdDate;
}