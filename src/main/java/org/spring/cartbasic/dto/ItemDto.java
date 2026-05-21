package org.spring.cartbasic.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    //상품
    //상품이미지
    //파일이 있을 경우 1, 없을경우 0
    private int attachFile;

    //input type="file
    private MultipartFile itemFile; // 실제 파일(이미지)

    private String newFileName;

    private String oldFileName;

    //View -> thymeleaf,react
    private Long memberId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
