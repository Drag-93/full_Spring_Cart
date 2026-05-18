package org.spring.cartbasic.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.spring.cartbasic.common.Role;
import org.spring.cartbasic.entity.ItemEntity;

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

}
