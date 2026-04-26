package com.retail.cart.infrastructure.client.connector;

import com.retail.cart.application.common.ErrorConstants;
import com.retail.cart.infrastructure.config.WebClientConfig;
import com.retail.cart.infrastructure.model.ProductServiceResponse;
import com.retail.core.common.exception.ServiceException;
import com.retail.core.common.exception.ValidationException;
import com.retail.core.common.model.AlertModel;
import com.retail.core.common.model.AlertType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CatalogConnectorImpl implements CatalogConnector{

  @Autowired
  private WebClientConfig webClientConfig;

    @CircuitBreaker(name = "catalogService", fallbackMethod = "fallbackCatalog")
    @Retry(name = "catalogService")
    public Mono<ProductServiceResponse> getById(Long id) {
        return webClientConfig.getCatalogWebClient()
                .get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductServiceResponse.class);
    }

    // fallback method
    public Mono<ProductServiceResponse> fallbackCatalog(Long id, Throwable ex) {

        // 1. Log the actual error for debugging
        log.error("Fallback triggered for product {}. Reason: {}", id, ex.getMessage());

        // 2. Build your Alert/Meta data
        AlertModel alert = AlertModel.builder()
                .alertType(AlertType.ERROR)
                .messageCode(ErrorConstants.CATALOG_SERVICE_UNAVAILABLE)
                .message("Product information can't be retrieved, please try after sometime.")
                .build();

        // 3. Create the response object (DO NOT throw an exception)
        ProductServiceResponse response = ProductServiceResponse.builder()
                .alertModel(alert) // Assuming your response object has a 'meta' or 'alert' field
                .build();

        // 4. Return as a normal "Successful" reactive signal
        return Mono.just(response);
    }
}