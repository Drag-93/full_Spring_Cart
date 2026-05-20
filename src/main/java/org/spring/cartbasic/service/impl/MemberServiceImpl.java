package org.spring.cartbasic.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.MemberDto;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.repository.MemberRepository;
import org.spring.cartbasic.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public List<MemberDto> memberList() {
        return memberRepository.findAll().stream()
                .map(el->MemberDto.builder()
                        .id(el.getId())
                        .userEmail(el.getUserEmail())
                        .userPw(el.getUserPw())
                        .role(el.getRole())
                        .itemEntities(el.getItemEntities())
                        .createTime(el.getCreateTime())
                        .updateTime(el.getUpdateTime())
                        .build()
                ).toList();
    }

    @Override
    public MemberDto memberDetail(Long id){
        Optional<MemberEntity> optionalMemberEntity=memberRepository.findById(id);
        if(optionalMemberEntity.isEmpty()){
            throw new NoSuchElementException("회원정보가 없습니다");
        }
            MemberEntity memberEntity=optionalMemberEntity.get();
        return MemberDto.toMemberDto(memberEntity);
    }
}
