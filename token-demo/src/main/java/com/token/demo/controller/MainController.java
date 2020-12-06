package com.token.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Value("${spring.application.name}")
    String appName;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("appName",appName);
		model.addAttribute("appMessage","Greetings from Spring Boot!");
		
		return "index";
		
	}

}

