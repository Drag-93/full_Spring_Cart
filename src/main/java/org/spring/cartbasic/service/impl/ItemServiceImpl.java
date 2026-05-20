package org.spring.cartbasic.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.ItemDto;
import org.spring.cartbasic.entity.ItemEntity;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.repository.ItemRepository;
import org.spring.cartbasic.repository.MemberRepository;
import org.spring.cartbasic.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    @Override
    public List<ItemDto> itemListFn() {
    //기존의 findAll() 대신 N+1 문제를 방지하는 findAllWithMember()사용
        return itemRepository.findAllWithMember().stream()
                .map(item -> ItemDto.builder()
                        .id(item.getId())
                        .itemTitle(item.getItemTitle())
                        .itemDetail(item.getItemDetail())
                        .itemPrice(item.getItemPrice())
                        .memberId(item.getMemberEntity().getId()) // 이제 추가 쿼리가 발생하지 않음
                        .build())
                .toList();
    }

    @Override
    public ItemDto itemDetailFn(Long id) {
        //1. 상품 조회(없을 경우 명확한 예외 메시지 처리)
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다. Id: " + id));

        //2. 상세 페이지 상품 수량 기본값 설정
        // 타인들의 장바구니 내역(getItemListEntities)을 조회하는 불필요한 로직과 추가 쿼리를 제거하고 기본값 1로 세팅
        int defaultSize = 1;

        return ItemDto.builder()
                .id(itemEntity.getId())
                .itemTitle(itemEntity.getItemTitle())
                .itemDetail(itemEntity.getItemDetail())
                .itemPrice(itemEntity.getItemPrice())
                .itemSize(defaultSize)
                .memberId(itemEntity.getMemberEntity().getId())
                .createTime(itemEntity.getCreateTime())
                .updateTime(itemEntity.getUpdateTime())
                .build();
    }

    @Override
    public void itemInsert(ItemDto itemDto) {
        memberRepository.findById(itemDto.getMemberId()).orElseThrow(()->{
            throw new IllegalArgumentException("회원정보가 없습니다.");
        });

        //파일(이미지 없을때)
        ItemEntity itemEntity = ItemEntity.builder()
                .itemTitle(itemDto.getItemTitle())
                .itemDetail(itemDto.getItemDetail())
                .itemPrice(itemDto.getItemPrice())
                .memberEntity(MemberEntity.builder().id(itemDto.getMemberId()).build())
                .build();
        itemRepository.save(itemEntity);
    }
}

