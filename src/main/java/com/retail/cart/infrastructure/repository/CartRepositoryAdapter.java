package com.retail.checkout.infrastructure.repository;

import com.retail.checkout.domain.model.CartItemModel;
import com.retail.checkout.domain.model.CartModel;
import com.retail.checkout.infrastructure.entity.CartEntity;
import com.retail.checkout.infrastructure.entity.CartItemEntity;
import com.retail.checkout.infrastructure.mapper.CartEntityToCartModelMapper;
import com.retail.checkout.infrastructure.mapper.CartItemEntityToCartItemModelMapper;
import com.retail.checkout.infrastructure.mapper.CartItemModelToCartItemEntityMapper;
import com.retail.checkout.infrastructure.mapper.CartModelToCartEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CartRepositoryAdapter implements CartRepository {

    @Autowired
    private CartJpaRepository cartJpaRepository;

    @Autowired
    private CartItemJpaRepository cartItemJpaRepository;

    @Autowired
    CartItemModelToCartItemEntityMapper cartItemModelToCartItemEntityMapper;

    @Autowired
    CartItemEntityToCartItemModelMapper cartItemEntityToCartItemModelMapper;

    @Autowired
    CartEntityToCartModelMapper cartEntityToCartModelMapper;

    @Autowired
    CartModelToCartEntityMapper cartModelToCartEntityMapper;

    public CartRepositoryAdapter(CartJpaRepository cartJpaRepository, CartItemJpaRepository cartItemJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartItemJpaRepository = cartItemJpaRepository;
    }

    @Override
    public CartModel addItem(CartModel cartModel) {
        CartEntity cartEntity;

        if (cartModel.getCartId() != null && cartModel.getCartId() > 0) {
            cartEntity = cartJpaRepository.findById(cartModel.getCartId()).orElse(null);
        } else {
            cartEntity = cartJpaRepository.findByUserIdAndIsActive(cartModel.getUserId(), true).orElse(null);
        }

        if (cartEntity != null) {
            CartItemModel itemToAdd = cartModel.getCartItemModels().getFirst();
            CartItemEntity existing = cartItemJpaRepository.findByCartIdAndItemId(cartEntity.getId(), itemToAdd.getItemNumber()).orElse(null);
            if (existing != null) {
                existing.setQuantity(existing.getQuantity() + itemToAdd.getQuantity());
                cartItemJpaRepository.save(existing);
            } else {
                CartItemEntity cartItemEntity = cartItemModelToCartItemEntityMapper.apply(itemToAdd);
                cartItemEntity.setCart(cartEntity);
                cartEntity.getCartItems().add(cartItemEntity);
                cartJpaRepository.save(cartEntity);
            }
            return cartEntityToCartModelMapper.apply(cartEntity);
        } else {
            CartEntity newCartEntity = cartModelToCartEntityMapper.apply(cartModel);
            if (newCartEntity.getCartItems() != null && !newCartEntity.getCartItems().isEmpty()) {
                newCartEntity.getCartItems().getFirst().setCart(newCartEntity);
            }
            cartJpaRepository.save(newCartEntity);
            return cartEntityToCartModelMapper.apply(newCartEntity);
        }
    }

    @Override
    public int deleteItem(Long cartId, Long itemId) {
        return cartItemJpaRepository.deleteByIdAndCartId(itemId, cartId);
    }

    @Override
    public CartModel getCart(Long cartId) {
        return cartJpaRepository.findActiveCartWithItems(cartId)
                .map(cartEntity -> cartEntityToCartModelMapper.apply(cartEntity)).orElse(null);
    }

    @Override
    public int getCartItemCount(Long userId) {
        return cartJpaRepository.findByUserIdAndIsActive(userId, true)
                .map(c -> c.getCartItems().stream().mapToInt(CartItemEntity::getQuantity).sum())
                .orElse(0);
    }

    @Override
    public java.util.List<CartItemModel> getCartItems(Long cartId) {
        return cartItemJpaRepository.findByCartId(cartId)
                .stream()
                .map(cartItemEntity -> cartItemEntityToCartItemModelMapper.apply(cartItemEntity))
                .toList();
    }

    @Override
    public CartModel getUserCart(Long userId) {
        return cartJpaRepository.findByUserIdAndIsActive(userId, true)
                .map(cartEntity -> cartEntityToCartModelMapper.apply(cartEntity))
                .orElse(null);
    }

    @Override
    public boolean makeInactive(Long cartId) {
        return cartJpaRepository.findById(cartId)
                .map(cartEntity -> {
                    cartEntity.setActive(false);
                    cartJpaRepository.save(cartEntity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public int updateQuantity(Long cartId, Long cartItemId, int quantity) {
        return cartItemJpaRepository.findByIdAndCartId(cartItemId, cartId)
                .map(cartItemEntity -> {
                    cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
                    cartItemJpaRepository.save(cartItemEntity);
                    return 1;
                })
                .orElse(0);
    }
}