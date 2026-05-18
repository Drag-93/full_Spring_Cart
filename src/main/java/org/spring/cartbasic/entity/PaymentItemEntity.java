package org.spring.cartbasic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.common.BasicTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_paymentItem_tb")
public class PaymentItemEntity extends BasicTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentItem_id")
    private Long id;

    private String paymentItemTitle;

    private int paymentItemPrice;

    private int paymentItemSize;

    //N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_id")
    private PaymentEntity paymentEntity;
}
