package com.aitorgonzalez.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToyController {
	@GetMapping("/toys")
	public String list() {
		return "toys";
	}
}
