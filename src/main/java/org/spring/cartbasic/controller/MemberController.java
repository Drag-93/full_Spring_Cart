package org.spring.cartbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.spring.cartbasic.dto.MemberDto;
import org.spring.cartbasic.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @GetMapping("/join")
    public String join(){
        return "member/join";
    }
    @PostMapping("/join")
    public String joinPost(MemberDto memberDto){
        //회원가입 실행
        memberService.memberInsert(memberDto);
        return "member/login";
    }



    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model){
        Object errorMessage = request.getSession().getAttribute("loginErrorMessage");
        if(errorMessage !=null){
            model.addAttribute("errorMessage",errorMessage);
            request.getSession().removeAttribute("loginErrorMessage");
        }
        return "member/login";
    }





}
