package com.wefox.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThreadLauncherController {
  @GetMapping("/threadlauncher")
  public String list() {
    return "threadlauncher";
  }
}
