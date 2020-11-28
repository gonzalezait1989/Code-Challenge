package com.aitorgonzalez.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.messaging.Message;

import com.aitorgonzalez.challenge.vo.PokemonVO;

import me.sargunvohra.lib.pokekotlin.model.Pokemon;

/**
 * Service to manage Pokemons.
 * 
 * @author aitor
 */
public interface PokemonService {

	public Optional<List<PokemonVO>> findByName(String name);

	/**
	 * Calls the Message Producer to create a new request to search for Pokemons.
	 * 
	 * @param name the name of the Pokemon.
	 * @return the generated message.
	 */
	public Optional<Message<String>> findByNameAsync(String name);

	/**
	 * Wraps a Pokemon into a Pokemon VO.
	 * 
	 * @param pokemon the pokemon to wrap
	 * @return the pokemon VO to return on the Controller
	 */
	default PokemonVO getPokemonVO(Pokemon pokemon) {
		return pokemon != null ? PokemonVO.builder().pokemon(pokemon).build() : PokemonVO.builder().build();
	}
}
