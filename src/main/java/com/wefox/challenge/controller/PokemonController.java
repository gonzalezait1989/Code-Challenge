package com.wefox.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PokemonController {
  @GetMapping("/pokemons")
  public String list() {
    return "pokemons";
  }
}
