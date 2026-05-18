package org.spring.cartbasic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.common.BasicTime;
import org.spring.cartbasic.common.Role;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart_member_tb")
public class MemberEntity extends BasicTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userPw;

    @Enumerated(EnumType.STRING)
    private Role role;

    //1:N
    @OneToMany(mappedBy = "memberEntity", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ItemEntity> itemEntities;


    @OneToMany(mappedBy = "memberEntity", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PaymentEntity> paymentEntities;


}
