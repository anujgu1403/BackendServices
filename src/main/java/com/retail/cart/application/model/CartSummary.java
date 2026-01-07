package com.retail.cart.application.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartSummary {
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;
}
