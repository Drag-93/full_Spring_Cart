package org.spring.cartbasic.controller;

import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.ItemListDto;
import org.spring.cartbasic.dto.PaymentDto;
import org.spring.cartbasic.service.CartService;
import org.spring.cartbasic.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final CartService cartService;
    //1. 주문/결제 작성 페이지 이동
    @GetMapping("/{memberId}")
    public String index(@PathVariable("memberId")Long memberId, Model model){
    List<ItemListDto> itemListDtos=cartService.cartList(memberId);
    model.addAttribute("itemList",itemListDtos);
    model.addAttribute("memberId",memberId);
    return "payment/index"; // 결제 페이지 뷰 경로
}
    //2. 비동기 결제 처리(AJAX)
    @PostMapping("/insert")
    @ResponseBody
    public String insert(@RequestBody PaymentDto paymentDto){
        paymentService.insertPayment(paymentDto);
        return "1"; //성공 응답값 리턴
    }

    //3. 주문 내역 목록 조회 페이지 이동
    @GetMapping("/paymentList/{memberId}")
    public String paymentList(@PathVariable("memberId")Long memberId, Model model){
        List<PaymentDto> paymentDtos=paymentService.paymentListFn(memberId);
        model.addAttribute("paymentList",paymentDtos);
        return "payment/paymentList";
    }
}
