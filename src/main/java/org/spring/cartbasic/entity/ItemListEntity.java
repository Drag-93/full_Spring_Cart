package org.spring.cartbasic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spring.cartbasic.common.BasicTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="cart_itemList_tb")
public class ItemListEntity extends BasicTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemList_id")
    private Long id;

    @Column(columnDefinition = "int default 0")
    private int itemSize;

    //N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private CartEntity cartEntity;

    //N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private ItemEntity itemEntity;


}
