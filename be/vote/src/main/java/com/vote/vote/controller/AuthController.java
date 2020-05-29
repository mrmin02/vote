package com.vote.vote.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Member;
import com.vote.vote.klaytn.Klaytn;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.service.KakaoAPIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    MemberJpaRepository memberRepository;
    
    @Autowired
    KakaoAPIService kakao;

    // @Autowired
   // // @Qualifier("kakaoRestTemplate")
   // private RestOperations kakaoRestTemplate;


    public AuthController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    private final OAuth2AuthorizedClientService authorizedClientService;
    
    public Klaytn klaytn = new Klaytn();

    

    @RequestMapping(value= {"","/"})
    public String login(){ // 로그인 뷰
        return "auth/login2";
    }

    @RequestMapping(value={"/oauth2/code/kakao","/oauth2/code/kakao/"}, produces ="application/json", method = RequestMethod.GET)
    public String kakao(@RequestParam("code") String code, RedirectAttributes ra, HttpServletResponse response
    ,RedirectAttributes redirectAttributes,HttpServletRequest request

    ) throws IOException{
        // String returnUrl = "redirect:/login.do?error";
      // ObjectNode result = (ObjectNode)kakaoRestTemplate.getForObject(
      //       "https://kapi.kakao.com/v2/user/me",ObjectNode.class);

        // System.out.println("result:  "+result);

        // System.out.println("kako code : "+ code);
        String access_Token = kakao.getAccessToken(code);
        // System.out.println("controller access_token : " + access_Token);

        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        
        System.out.println("login Controller : " + userInfo);
        
        // System.out.println(session);

        Member member = memberRepository.findByUserid(userInfo.get("id").toString());
        // 회원가입 되지 않은 경우.
        if (member == null) {
            Member newMember = new Member();
            newMember.setUserid(userInfo.get("id").toString());
            newMember.setName(userInfo.get("name").toString());
            if(userInfo.get("img")!=null){
                newMember.setProfile(userInfo.get("img").toString());
            }else{
                newMember.setProfile("/img/defaultProfile.png");
            }
            
            SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
          Date time = new Date();
          String time1 = format1.format(time);
       
            newMember.setJoindate(time1);
            newMember.setRole("0");
             
            memberRepository.saveAndFlush(newMember);
            System.out.println("회원가입 완료");
            try{
                // createPrivateKey(newMember);
            }catch(Exception e ){
                System.out.println("카카로계정 회원가입에서 키 생성 오류");
            }
            
        }
        // 회원가입 된 후  ( 이미 되어 있거나 방금 코드로 로그인 된 경우.)
        Member user= memberRepository.findByUserid(userInfo.get("id").toString());
        if(userInfo.get("img")!=null){
            user.setProfile(userInfo.get("img").toString());
        }else{
            user.setProfile("/img/defaultProfile.png");
        }
        memberRepository.saveAndFlush(user);
        

       
        // UserDetails.
//         session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        //  UserDetails 를 상속받아서 더 많은 정보를 가질 수 있게 해야 함.
        // UserDetails user = 
        // User
        // .withUsername(userInfo.get("id").toString())
        //     .password(userInfo.get("id").toString())
        //     .username(userInfo.get("id").toString())
        //     .authorities(AuthorityUtils.createAuthorityList("USER"))
        //     .roles("USER")
        // .build();
        CustomUserDetails user2 = new CustomUserDetails();
        user2.setID(userInfo.get("id").toString());
        user2.setPASSWORD(userInfo.get("id").toString());
        user2.setNAME(userInfo.get("name").toString());
        if(userInfo.get("img")!=null){
            user2.setIMG(userInfo.get("img").toString());
        }else{
            user2.setIMG("/img/defaultProfile.png");
        }
        
        user2.setAUTHORITY("USER");

        final HttpSession session = request.getSession();
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user2, "null", AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        return "redirect:/";
    }

    @RequestMapping(value= {"/register","/register/"})
    public String register(){ // 회원가입 뷰
        return "auth/register";
    }
    
    @ResponseBody
    @RequestMapping(value= {"/register/checkId/{id}","/register/checkId/{id}/"},
    method=RequestMethod.GET,
   produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject register_check_id(@PathVariable("id") String id){ // 로그인 뷰
        JSONObject result = new JSONObject();
        System.out.println(id);
        

        Member member = memberRepository.findByUserid(id);
        if(member == null){
            result.put("message", "사용 가능한 아이디 입니다.");
            result.put("check", 1);
        }else{
            result.put("message", "이미 존재하는 아이디입니다.");
            result.put("check", 0);
        }
        System.out.println(result);
        return  result;
    }

    // @Slf4j
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerOk(Member mm, RedirectAttributes redirAttrs){

        System.out.println("mm: "+mm);        

        if(memberRepository.findByUserid(mm.getUserid()) == null){

            SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
          Date time = new Date();
          String time1 = format1.format(time);
       
           mm.setJoindate(time1);
           mm.setRole("0");
            memberRepository.saveAndFlush(mm);
            return "redirect:/auth";
        }else{
            redirAttrs.addFlashAttribute("message", "아이디 중복확인을 해 주세요");
            return "redirect:/auth/register";
        }

        
        
        
    }
    public void createPrivateKey(Member data) throws Exception{
        ExecutorService es = Executors.newCachedThreadPool();
        
        // es.execute(() -> {
        //     try {
        //         JSONObject json = klaytn.createKey();
        //         System.out.println(json);
        //         data.setPrivateKey(json.get("privateKey").toString());
        //         data.setAddress(json.get("address").toString());
        //         memberRepository.saveAndFlush(data);
        //         System.out.println("저장 성공?");
        //     } catch (Exception e) {
        //         System.out.println("클레이튼 오류 발생 : 계정 생성");
        //     }
        // });
    }
}