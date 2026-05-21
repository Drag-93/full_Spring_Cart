package org.spring.cartbasic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.common.BasicTime;
import org.spring.cartbasic.dto.ItemDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="cart_item_tb")
public class ItemEntity extends BasicTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String itemTitle;

    @Column(nullable = false)
    private String itemDetail;

    @Column(nullable = false)
    private int itemPrice;


    //파일 유무 체크
    @Column(nullable = true, columnDefinition = "int default 0")
    private int attachFile;

    //1:N
    @OneToMany(mappedBy = "itemEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<FileEntity> fileEntities;

    //hit 생략

    //N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    //1:N
    @OneToMany(mappedBy = "itemEntity",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<ItemListEntity> itemListEntities;


}
