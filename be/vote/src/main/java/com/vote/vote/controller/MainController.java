package com.vote.vote.controller;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index() {
		// System.out.println("/ --> index");
		// if(user != null){
		// 	// UserDetails u = (UserDetails)user;
		// 	System.out.println(u);
		// }
		return "main_index";
	}
	// @RequestMapping("/about")
	// public String about() {
	// 	// System.out.println("/ --> home");
	// 	return "about";
	// }
	// @RequestMapping("/blog")
	// public String blog() {
	// 	// System.out.println("/ --> home");
	// 	return "blog";
	// }
	// @RequestMapping("/contact")
	// public String contact() {
	// 	// System.out.println("/ --> home");
	// 	return "contact";
	// }
}