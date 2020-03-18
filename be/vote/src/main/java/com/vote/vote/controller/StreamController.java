package com.vote.vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stream")
public class StreamController {

	@RequestMapping(value={"","/"})
	public String index() {
		return "/stream/index";
	}
}