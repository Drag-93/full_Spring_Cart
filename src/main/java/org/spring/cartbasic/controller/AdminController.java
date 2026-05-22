package org.spring.cartbasic.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.ItemDto;
import org.spring.cartbasic.dto.MemberDto;
import org.spring.cartbasic.dto.PaymentDto;
import org.spring.cartbasic.service.ItemService;
import org.spring.cartbasic.service.MemberService;
import org.spring.cartbasic.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PaymentService paymentService;
    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping({"","dashboard"})
    public String index(){
        return "admin/index";
    }



    //=====================payment========================
    @GetMapping("/payment")
    public String payment(Model model){
        List<PaymentDto> paymentDtos= paymentService.paymentAllList();
        model.addAttribute("paymentList",paymentDtos);
        System.out.println(paymentDtos);
        return "admin/payment/payment";
    }


    //========================item======================
    @GetMapping("/item")
    public String item(Model model){
        List<ItemDto> itemDtos=itemService.itemListFn();
        model.addAttribute("itemList",itemDtos);
        return "admin/item/item";
    }
    @GetMapping("/item/detail/{id}")
    public String itemDetail(@PathVariable Long id, Model model){
        ItemDto itemDto = itemService.itemDetailFn(id);
        System.out.println(itemDto.toString());
        model.addAttribute("item", itemDto);
        return "admin/item/itemDetail";
    }
    @GetMapping("/item/write")
    public String itemInsert(){
        return "admin/item/itemWrite";
    }
    @PostMapping("/item/write")
    public String itemWriteOk(@Valid ItemDto itemDto) throws IOException {
        itemService.itemInsert(itemDto);
        return "redirect:/admin/item";
    }


    //========================member======================
    @GetMapping("/member")
    public String member(Model model){
        List<MemberDto> memberDtos= memberService.memberList();
        model.addAttribute("memberList",memberDtos);
        return "admin/member/member";
    }
    @GetMapping("/member/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model){
        MemberDto memberDto  = memberService.memberDetail(id);
        model.addAttribute("member",memberDto);
        return "admin/member/memberDetail";
    }
}
