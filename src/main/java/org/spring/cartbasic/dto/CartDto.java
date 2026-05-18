package org.spring.cartbasic.dto;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.entity.ItemListEntity;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.entity.PaymentEntity;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private Long id;

    private Long memberId;

    private MemberEntity memberEntity;

    List<ItemListEntity> itemListEntities;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
