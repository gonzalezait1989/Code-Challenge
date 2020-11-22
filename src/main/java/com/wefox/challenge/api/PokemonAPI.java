package com.wefox.challenge.api;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wefox.challenge.service.PokemonService;
import com.wefox.challenge.vo.PokemonVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/pokemons")
@Slf4j
@RequiredArgsConstructor
public class PokemonAPI {

	@Autowired
	private final PokemonService pokemonService;

	@GetMapping("/{parameter}")
	public ResponseEntity<List<PokemonVO>> findByName(@PathVariable String name) {
		Optional<List<PokemonVO>> pokemons = pokemonService.findByName(name);
		if (!pokemons.isPresent()) {
			log.error("Pokemon with name " + name + " does not exist");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(pokemons.get());
	}

}
