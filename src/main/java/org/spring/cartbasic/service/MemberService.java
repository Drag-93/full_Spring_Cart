package org.spring.cartbasic.service;

import org.spring.cartbasic.dto.MemberDto;

import java.util.List;

public interface MemberService {
    void memberInsert(MemberDto memberDto);

    List<MemberDto> memberList();

    MemberDto memberDetail(Long id);
}
