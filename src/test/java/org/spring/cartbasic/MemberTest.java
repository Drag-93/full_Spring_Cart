package org.spring.cartbasic;

import org.junit.jupiter.api.Test;
import org.spring.cartbasic.common.Role;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void insert(){
        for(int i=1; i<=10;i++){
            memberRepository.save(MemberEntity.builder()
                            .userPw("11").userEmail("m"+i+"@email.com")
                            .role(Role.MEMBER).build());
        }
    }
}
