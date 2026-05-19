package org.spring.cartbasic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.common.BasicTime;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "cart_payment_tb")
public class PaymentEntity extends BasicTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long id;
    //주문방법
    private String paymentType;
    //주문처
    private String orderPost;
    //배송주소
    private String orderAddr;
    //주문금액
    private String payResult;
    private String orderMethod;

    //N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    //1:N
    @OneToMany(mappedBy = "paymentEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CartEntity> cartEntities;

    @OneToMany(mappedBy = "paymentEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PaymentItemEntity> paymentItemEntities;


}


