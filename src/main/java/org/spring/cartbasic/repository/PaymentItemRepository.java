package org.spring.cartbasic.repository;

import org.spring.cartbasic.entity.CartEntity;
import org.spring.cartbasic.entity.PaymentItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentItemRepository extends JpaRepository<PaymentItemEntity, Long> {
}
