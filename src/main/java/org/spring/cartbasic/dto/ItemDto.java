package org.spring.cartbasic.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;

    private String itemTitle;

    private String itemDetail;

    private int itemPrice;

    private int itemSize; // -> ItemList itemSize

    //View -> thymeleaf,react
    private Long memberId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
