package com.vote.vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index() {
		// System.out.println("/ --> index");
		return "index.html";
	}
	@RequestMapping("/home")
	public String home() {
		// System.out.println("/ --> home");
		return "home.html";
	}
}