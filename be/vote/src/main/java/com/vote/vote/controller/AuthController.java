package com.vote.vote.controller;

import com.vote.vote.db.dto.Member;
import com.vote.vote.repository.MemberJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
	MemberJpaRepository MemberRepository;

    @RequestMapping(value= {"","/"})
    public String login(){ // 로그인 뷰
        return "auth/login";
    }

    @RequestMapping(value= {"/register","/register/"})
    public String register(){ // 회원가입 뷰
        return "auth/register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerOk(
            @RequestParam("id") String id,
            @RequestParam("password") String pwd,
            @RequestParam("username") String name,
            @RequestParam("birth") String birth,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email){

        Member data = new Member();
        
        data.setMemberId(id);
        data.setPassword(pwd);
        data.setName(name);
        data.setBirth(birth);
        data.setPhone(phone);
        data.setEmail(email);

        MemberRepository.saveAndFlush(data);
        
        return "redirect:/auth";
    }
    
}