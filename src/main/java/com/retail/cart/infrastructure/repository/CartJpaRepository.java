package com.retail.cart.infrastructure.repository;

import com.retail.cart.infrastructure.entity.CartEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByUserIdAndIsActive(Long userId, boolean active);

    @Query("""
        SELECT c FROM CartEntity c
        LEFT JOIN FETCH c.cartItems
        WHERE c.id = :id AND c.isActive = true
        """)
    Optional<CartEntity> findActiveCartWithItems(@Param("id") Long id);
}