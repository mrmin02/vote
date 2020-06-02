package com.vote.vote.controller;

import com.vote.vote.config.CustomUserDetails;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index() {

			
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