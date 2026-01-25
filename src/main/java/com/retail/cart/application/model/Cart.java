package com.retail.cart.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cart {
    private Long cartId;
    private Long userId;
    private LocalDateTime createdDate;
    private boolean isActive;
    private List<CartItem> cartItems;
    private CartSummary cartSummary;
}