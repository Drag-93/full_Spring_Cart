package org.spring.cartbasic.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.spring.cartbasic.common.Role;
import org.spring.cartbasic.entity.CartEntity;
import org.spring.cartbasic.entity.ItemEntity;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemListDto {
    private Long id;

    private int itemSize;

    private CartEntity cartEntity;

    private ItemEntity itemEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
