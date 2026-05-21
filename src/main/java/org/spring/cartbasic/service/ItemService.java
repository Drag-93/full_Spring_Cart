package org.spring.cartbasic.service;

import jakarta.validation.Valid;
import org.spring.cartbasic.dto.ItemDto;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    List<ItemDto> itemListFn();

    ItemDto itemDetailFn(Long id);

    void itemInsert(ItemDto itemDto) throws IOException;
}
