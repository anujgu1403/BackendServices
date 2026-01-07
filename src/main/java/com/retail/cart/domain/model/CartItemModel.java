package com.retail.checkout.domain.model;

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
    private Long itemNumber;
    private BigDecimal unitPrice;
    private int quantity;
    private Long cartId;
    private LocalDateTime createdDate;
}