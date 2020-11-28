package com.aitorgonzalez.challenge.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgonzalez.challenge.exceptions.InternalErrorException;
import com.aitorgonzalez.challenge.exceptions.ResourceNotFoundException;
import com.aitorgonzalez.challenge.service.PokemonService;
import com.aitorgonzalez.challenge.vo.PokemonVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pokemons")
@RequiredArgsConstructor
public class PokemonApi {

	@Autowired
	private final PokemonService pokemonService;

	@GetMapping("/{parameter}")
	public ResponseEntity<List<PokemonVO>> findByName(@PathVariable String name) {
		return pokemonService.findByName(name).map(ResponseEntity::ok).orElseThrow(ResourceNotFoundException::new);
	}

	@GetMapping("/queue/{parameter}")
	public ResponseEntity<Message<String>> findByNameAsyncRequest(@PathVariable String name) {
		return pokemonService.findByNameAsync(name).map(ResponseEntity::ok).orElseThrow(InternalErrorException::new);
	}

}
