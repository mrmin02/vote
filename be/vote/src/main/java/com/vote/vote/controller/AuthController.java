package com.vote.vote.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.vote.vote.db.dto.Member;
import com.vote.vote.klaytn.Klaytn;
import com.vote.vote.repository.MemberJpaRepository;

import org.json.simple.JSONObject;
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
    
    public Klaytn klaytn = new Klaytn();

    @RequestMapping(value= {"","/"})
    public String login(){ // 로그인 뷰
        return "auth/login";
    }

    @RequestMapping(value= {"/register","/register/"})
    public String register(){ // 회원가입 뷰
        return "auth/register";
    }

    // @Slf4j
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
        

        ExecutorService es = Executors.newCachedThreadPool();
        
        es.execute(() -> {
            try {
                JSONObject json = klaytn.createKey();
                System.out.println(json);
                data.setPrivateKey(json.get("privateKey").toString());
                data.setAddress(json.get("address").toString());
                MemberRepository.saveAndFlush(data);
                System.out.println("저장 성공?");
            } catch (Exception e) {
                System.out.println("클레이튼 오류 발생 : 계정 생성");
            }
        });

        // Future f = es.submit(() -> {
        //     try {
        //         JSONObject json = klaytn.createKey();
        //         System.out.println(json);
        //         data.setPrivateKey(json.get("privagteKey").toString());
        //         data.setAddress(json.get("address").toString());
        //         MemberRepository.saveAndFlush(data);
        //         System.out.println("저장 성공?");
        //     } catch (Exception e) {
        //         System.out.println("클레이튼 오류 발생 : 계정 생성");
        //     }
        // });
        
        return "redirect:/auth";
    }
    
}