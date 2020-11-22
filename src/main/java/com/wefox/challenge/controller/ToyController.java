package com.wefox.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToyController {
    @GetMapping("/toy/")
    public String list(){
        return "toys";
    }
}
