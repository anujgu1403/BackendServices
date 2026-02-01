package com.retail.cart.application.service.impl;

import com.retail.cart.application.mapper.CartItemModelToCartItemMapper;
import com.retail.cart.application.mapper.CartModelToCartMapper;
import com.retail.cart.application.mapper.CartToCartModelMapper;
import com.retail.cart.application.model.Cart;
import com.retail.cart.application.model.CartItem;
import com.retail.cart.application.service.CartService;
import com.retail.cart.domain.model.CartModel;
import com.retail.cart.infrastructure.client.connector.CatalogConnectorImpl;
import com.retail.cart.infrastructure.client.integrator.CatalogIntegratorImpl;
import com.retail.cart.infrastructure.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    CartModelToCartMapper cartModelToCartMapper;

    @Autowired
    CartToCartModelMapper cartToCartModelMapper;

    @Autowired
    CartItemModelToCartItemMapper cartItemModelToCartItemMapper;

    @Autowired
    CatalogIntegratorImpl catalogIntegrator;

    @Override
    public Cart getUserCart(Long userId) {
        var cartModel = cartRepository.getUserCart(userId);
        return cartModel == null ? null : cartModelToCartMapper.apply(cartModel);
    }

    @Override
    public int getCartItemCount(Long userId) {
        return cartRepository.getCartItemCount(userId);
    }

    @Override
    public List<CartItem> getCartItems(Long cartId) {
        return Optional.ofNullable(cartRepository.getCartItems(cartId))
                .map(cartItemModels -> cartItemModels
                        .stream()
                        .map(cartItemModel -> cartItemModelToCartItemMapper.apply(cartItemModel))
                        .toList()
                ).orElse(null);
    }

    @Override
    public Cart getCart(Long cartId) {
        var cartModel = cartRepository.getCart(cartId);
        return cartModel == null ? null : cartModelToCartMapper.apply(cartModel);
    }

    @Override
    public Mono<Cart> addItem(Cart cart) {

        return Mono.just(cart)
                .map(cartToCartModelMapper::apply)
                .flatMap(cartModel ->
                        Flux.fromIterable(cartModel.getCartItemModels())
                                .flatMap(catalogIntegrator::integrate)
                                .collectList()
                                .map(enrichedItems -> {
                                    cartModel.setCartItemModels(enrichedItems);
                                    return cartModel;
                                })
                )
                .flatMap(cartRepository::addItem)
                .map(cartModelToCartMapper::apply);
    }

    @Override
    public int deleteItem(Long cartId, Long itemId) {
        return cartRepository.deleteItem(cartId, itemId);
    }

    @Override
    public boolean makeInactive(Long cartId) {
        return cartRepository.makeInactive(cartId);
    }

    @Override
    public int updateQuantity(Long cartId, Long cartItemId, int quantity) {
        return cartRepository.updateQuantity(cartId, cartItemId, quantity);
    }
}