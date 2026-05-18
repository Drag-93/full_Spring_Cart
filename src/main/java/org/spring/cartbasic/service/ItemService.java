package org.spring.cartbasic.service;

import org.spring.cartbasic.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> itemListFn();

    ItemDto itemDetailFn(Long id);
}
