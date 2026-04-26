package com.retail.cart.infrastructure.model;

import com.retail.core.common.model.AlertModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class ProductServiceResponse {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
    private Integer categoryId;
    private OffsetDateTime createdDate;
    private AlertModel alertModel;
}
