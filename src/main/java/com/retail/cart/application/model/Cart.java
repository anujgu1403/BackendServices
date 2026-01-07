package com.retail.checkout.application.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    private Long cartId;
    private Long userId;
    private LocalDateTime createdDate;
    private boolean isActive;
    private List<CartItem> cartItems;
    private CartSummary cartSummary;
}