package org.spring.cartbasic.dto;

import lombok.*;
import org.spring.cartbasic.entity.ItemEntity;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class FileDto {
    private Long id;

    private String newFileName;

    private String oldFileName;

    private ItemEntity itemEntity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
