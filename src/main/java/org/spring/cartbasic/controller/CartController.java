package org.spring.cartbasic.controller;

import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.ItemDto;
import org.spring.cartbasic.dto.ItemListDto;
import org.spring.cartbasic.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    //1. 장바구니 담기(기본Form 전송 방식)
    @PostMapping("/insert/addCart0")
    public String addCart0(@ModelAttribute ItemDto itemDto) {
        cartService.insertCart2(itemDto);
        return "redirect:/cart/cartList1/" + itemDto.getMemberId();
    }
     //2. 장바구니 담기(비동기 AJAX/JASON요청 방식)
     @PostMapping("/insert/addCart2")
     @ResponseBody
     public String addCart2(@RequestBody ItemDto itemDto){
         cartService.insertCart2(itemDto);
         return "1"; // 성공 시 1
        }
     //3. 장바구니 담기(URL 경로 파라미터 방식 - 수량 1개 고정)
    @GetMapping("/insert/memberId/{memberId}/id/{id}")
    public String addCart1(@PathVariable("memberId")Long memberId,
                           @PathVariable("id")Long id){
        cartService.insertCart(memberId,id);
        return "redirect:/cart/cartList1/"+memberId;
    }

    //4. 본인 장바구니 목록 조회 화면
    @GetMapping("/cartList1/{memberId}")
    public String cartList1(@PathVariable("memberId")Long memberId, Model model){
        List<ItemListDto>itemListDtos=cartService.cartList(memberId);
        model.addAttribute("itemList",itemListDtos);
        model.addAttribute("memberId",memberId);
        return "item/cartList1";
    }

    @GetMapping("/delete/{memberId}/{itemListId}")
    public String deleteCartList(@PathVariable("memberId")Long memberId,@PathVariable("itemListId")Long itemListId
    ){
        cartService.deleteCart(itemListId);
        return "redirect:/cart/cartList1/" + memberId;
    }

    }

