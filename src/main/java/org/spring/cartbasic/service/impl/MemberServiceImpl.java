package org.spring.cartbasic.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.MemberDto;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.repository.MemberRepository;
import org.spring.cartbasic.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void memberInsert(MemberDto memberDto) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserEmail(memberDto.getUserEmail());
        if(optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일");
        }
        memberRepository.save(MemberEntity.toInsertMemberEntity(memberDto,passwordEncoder));

    }
}
