package com.retail.cart.infrastructure.repository;

import com.retail.cart.infrastructure.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {

    Optional<CartItemEntity> findByIdAndCartId(Long id, Long cartId);

    Optional<CartItemEntity> findByCartIdAndProductId(Long cartId, Long productId);

    int deleteByIdAndCartId(Long id, Long cartId);

    List<CartItemEntity> findByCartId(Long cartId);
}