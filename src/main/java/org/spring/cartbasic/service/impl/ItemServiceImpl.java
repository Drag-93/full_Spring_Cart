package org.spring.cartbasic.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.FileDto;
import org.spring.cartbasic.dto.ItemDto;
import org.spring.cartbasic.entity.FileEntity;
import org.spring.cartbasic.entity.ItemEntity;
import org.spring.cartbasic.entity.MemberEntity;
import org.spring.cartbasic.repository.FileRepository;
import org.spring.cartbasic.repository.ItemRepository;
import org.spring.cartbasic.repository.MemberRepository;
import org.spring.cartbasic.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;
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
                .attachFile(itemEntity.getAttachFile())
                .newFileName(itemEntity.getFileEntities().get(0).getNewFileName())
                .oldFileName(itemEntity.getFileEntities().get(0).getOldFileName())
                .memberId(itemEntity.getMemberEntity().getId())
                .createTime(itemEntity.getCreateTime())
                .updateTime(itemEntity.getUpdateTime())
                .build();
    }
//    파일 추가 전
//    @Override
//    public void itemInsert(ItemDto itemDto) {
//        memberRepository.findById(itemDto.getMemberId()).orElseThrow(()->{
//            throw new IllegalArgumentException("회원정보가 없습니다.");
//        });
//
//        //파일(이미지 없을때)
//        ItemEntity itemEntity = ItemEntity.builder()
//                .itemTitle(itemDto.getItemTitle())
//                .itemDetail(itemDto.getItemDetail())
//                .itemPrice(itemDto.getItemPrice())
//                .memberEntity(MemberEntity.builder().id(itemDto.getMemberId()).build())
//                .build();
//        itemRepository.save(itemEntity);
//    }


    //파일 추가
    @Override
    public void itemInsert(ItemDto itemDto) throws IOException {
        //1. 작성자 확인
        memberRepository.findById(itemDto.getMemberId()).orElseThrow(()->{
            throw new IllegalArgumentException("회원정보가 없습니다.");
        });
        MemberEntity memberEntity= MemberEntity.builder()
                .id(itemDto.getMemberId())
                .build();

        //2. 파일 없는 경우
        if(itemDto.getItemFile().isEmpty()){
            ItemEntity noFileItemEntity=ItemEntity.builder()
                    .memberEntity(memberEntity)
                    .attachFile(0)
                    .itemPrice(itemDto.getItemPrice())
                    .itemTitle(itemDto.getItemTitle())
                    .itemDetail(itemDto.getItemDetail())
                    .build();
            itemRepository.save(noFileItemEntity);
            return;
        }
        //3. 파일 있는 경우
        MultipartFile itemFile = itemDto.getItemFile();
        String oldFileName=itemFile.getOriginalFilename();
        String newFileName= UUID.randomUUID()+"_"+oldFileName;
        String fileSavePath="E:/full/upload/test0521/"+ newFileName;
        //파일 저장
        itemFile.transferTo(new File(fileSavePath));
        //게시글 저장
        ItemEntity withFileItemEntity=ItemEntity.builder()
                .memberEntity(memberEntity)
                .attachFile(1)
                .itemPrice(itemDto.getItemPrice())
                .itemTitle(itemDto.getItemTitle())
                .itemDetail(itemDto.getItemDetail())
                .build();
        ItemEntity saveItem=itemRepository.save(withFileItemEntity);
        //파일 DB저장
//        FileDto fileDto=FileDto.builder()
//                .oldFileName(oldFileName)
//                .newFileName(newFileName)
//                .itemEntity(savItem)
//                .build();
        fileRepository.save(FileEntity.builder()
                        .itemEntity(saveItem)
                        .oldFileName(oldFileName)
                        .newFileName(newFileName)
                        .build());
    }


}

