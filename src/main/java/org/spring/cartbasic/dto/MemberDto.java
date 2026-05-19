package org.spring.cartbasic.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.spring.cartbasic.common.Role;
import org.spring.cartbasic.entity.ItemEntity;
import org.spring.cartbasic.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;

    private String userEmail;

    private String userPw;

    private Role role;

    List<ItemEntity> itemEntities;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;



    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto=new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setUserEmail(memberEntity.getUserEmail());
        memberDto.setUserPw(memberEntity.getUserPw());
        memberDto.setRole(memberEntity.getRole());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        memberDto.setUpdateTime(memberEntity.getUpdateTime());
        return memberDto;
    }

}
