package com.retail.cart.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartSummaryModel {
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;
}
