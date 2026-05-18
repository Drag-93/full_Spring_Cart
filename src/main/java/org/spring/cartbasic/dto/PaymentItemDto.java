package org.spring.cartbasic.dto;

import lombok.*;
import org.spring.cartbasic.entity.PaymentEntity;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentItemDto {
    private Long id;

    private String paymentItemTitle;

    private int paymentItemPrice;

    private int paymentItemSize;

    private PaymentEntity paymentEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
