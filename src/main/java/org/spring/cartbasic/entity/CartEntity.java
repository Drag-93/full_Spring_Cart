package org.spring.cartbasic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.common.BasicTime;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart_cart_tb")
public class CartEntity extends BasicTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    //1:1 -> 한쪽만 설정
    @OneToOne
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    //1:N
    @OneToMany(mappedBy = "cartEntity",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    List<ItemListEntity> itemListEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_id")
    private PaymentEntity paymentEntity;

}
