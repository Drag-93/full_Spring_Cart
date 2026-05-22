package org.spring.cartbasic.controller;

import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.ItemDto;
import org.spring.cartbasic.service.ItemService;
import org.spring.cartbasic.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping({"", "/itemList"})
    public String index(Model model) {
        List<ItemDto> itemList = itemService.itemListFn();
        model.addAttribute("itemList", itemList);
        return "item/itemList";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")Long id, Model model){
        ItemDto item = itemService.itemDetailFn(id);
        model. addAttribute("item",item);
        return "item/itemDetail";
    }


}
