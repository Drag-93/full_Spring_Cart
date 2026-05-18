package org.spring.cartbasic;

import org.junit.jupiter.api.Test;
import org.spring.cartbasic.common.Role;
import org.spring.cartbasic.entity.ItemEntity;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.repository.ItemRepository;
import org.spring.cartbasic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void insert(){
        for(int i=1; i<=10;i++){
            itemRepository.save(ItemEntity.builder()
                            .itemTitle("상품"+i).itemDetail("상품"+i+"상세 내역")
                            .itemPrice(10000*i+5000)
                            .memberEntity(MemberEntity.builder().id(1L).build())
                            .build());
        }
    }

    @Test
    void detail(){
        ItemEntity itemEntity=
                itemRepository.findById(3L).orElseThrow(IllegalArgumentException::new);
        System.out.println(itemEntity);
    }
}
