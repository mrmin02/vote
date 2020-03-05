package com.vote.vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index() {
		// System.out.println("/ --> index");
		return "index2";
	}
	@RequestMapping("/home")
	public String home() {
		// System.out.println("/ --> home");
		return "home";
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