package com.retail.cart.infrastructure.client.integrator;

import com.retail.cart.domain.model.CartItemModel;
import com.retail.cart.infrastructure.client.connector.CatalogConnectorImpl;
import com.retail.cart.infrastructure.mapper.ProductServiceResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CatalogIntegratorImpl implements CatalogIntegrator{

    @Autowired
    private CatalogConnectorImpl catalogConnector;

    @Autowired
    private ProductServiceResponseMapper productServiceResponseMapper;

    public Mono<CartItemModel> integrate(CartItemModel cartItemModel){
        return Mono.just(cartItemModel)
                .flatMap(cartItem->catalogConnector.getById(cartItemModel.getProductId()))
                .map(response -> productServiceResponseMapper.apply(response, cartItemModel))
                .log("Product service called.");

    }
}
