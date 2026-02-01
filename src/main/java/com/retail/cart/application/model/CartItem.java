package com.retail.cart.application.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private String description;
    private String imageUrl;
    private BigDecimal unitPrice;
    private int quantity;
    private LocalDateTime createdDate;
}