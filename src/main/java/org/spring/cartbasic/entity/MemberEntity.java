package org.spring.cartbasic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.common.BasicTime;
import org.spring.cartbasic.common.Role;
import org.spring.cartbasic.dto.MemberDto;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    // 회원가입
    public static MemberEntity toInsertMemberEntity(MemberDto memberDto , PasswordEncoder passwordEncoder) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(passwordEncoder.encode(memberDto.getUserPw()));
        memberEntity.setRole(Role.MEMBER);

        return memberEntity;
    }
    // 회원수정
    public static MemberEntity toUpdateMemberEntity(MemberDto memberDto) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setId(memberDto.getId());
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setRole(memberDto.getRole());

        return memberEntity;
    }
}
