package com.vote.vote.controller;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@RequestMapping(value={"","/"})
	public String index() {        
        return "userInfo/index";
	}

}