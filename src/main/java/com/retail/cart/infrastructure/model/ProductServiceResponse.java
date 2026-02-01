package com.retail.cart.infrastructure.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ProductServiceResponse {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
    private Integer categoryId;
    private OffsetDateTime createdDate;
}
