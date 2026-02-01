package com.retail.cart.infrastructure.client.connector;

import com.retail.cart.infrastructure.config.WebClientConfig;
import com.retail.cart.infrastructure.model.ProductServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CatalogConnectorImpl implements CatalogConnector{

  @Autowired
  private WebClientConfig webClientConfig;

    public Mono<ProductServiceResponse> getById(Long id) {
        return webClientConfig.getCatalogWebClient()
                .get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductServiceResponse.class);


    }
}