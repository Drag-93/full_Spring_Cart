package org.spring.cartbasic.service;

import org.spring.cartbasic.dto.ItemDto;
import org.spring.cartbasic.dto.ItemListDto;

import java.util.List;

public interface CartService {

    void insertCart(Long memberId, Long id);

    List<ItemListDto> cartList(Long memberId);

    void insertCart2(ItemDto itemDto);

    void deleteCart(Long id);
}
