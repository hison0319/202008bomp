package com.bomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping(value = "/goError", method = RequestMethod.GET)
	public String goError() {
		return "error/error_page";
	}
}
