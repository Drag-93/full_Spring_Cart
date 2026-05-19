package org.spring.cartbasic.dto;

import lombok.*;
import org.spring.cartbasic.entity.CartEntity;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.entity.PaymentItemEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long id;

    private String paymentType;

    private String orderPost;

    private String orderAddr;

    private String payResult;

    private String orderMethod;

    private Long memberId;

    private Long cartId;

    private MemberEntity memberEntity;

    private List<CartEntity> cartEntities;

    private List<PaymentItemEntity> paymentItemEntities;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
