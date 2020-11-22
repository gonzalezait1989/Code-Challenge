package com.wefox.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wefox.challenge.vo.PokemonVO;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

@Service
public class PokemonService {

	private PokeApiClient pokeApiClient =  new PokeApiClient();
	
	/**
	 * Finds pokemons by name.
	 * @param name the name of the pokemon.
	 * @return an optional List of Pokemons if found.
	 */
	public Optional<List<PokemonVO>> findByName(String name) {
		if(name == null || name.isBlank()) {
			return Optional.empty();
		}
		int elements = 100;
		int offset = 0;
		List<Pokemon> pokemons = new ArrayList<Pokemon>();
		// There are 1050 pokemons, but I do it in batches of 100 to make sure that if
		// some more are added, we don't miss them.
		while (elements == 100) {
			// A list of referenced resources. I am fetching all the resources this way
			// because the API does not provide an endpoint to search by proximations of the name (startswith),
			// just the full name
			NamedApiResourceList pokemonList = this.pokeApiClient.getPokemonList(offset, elements);
			pokemonList.component4().parallelStream().forEach(resource -> {
				if (resource.getName().toLowerCase().startsWith(name.toLowerCase())) {
					pokemons.add(this.pokeApiClient.getPokemon(resource.getId()));
				}
			});
			// When we had less than 100 elements, this will be the end of the looping
			elements = pokemonList.getCount();
			offset += pokemonList.getCount();
		}

		if (pokemons.isEmpty())
			return Optional.empty();
		return Optional.of(pokemons.parallelStream().map(pokemon -> 
			getPokemonVO(pokemon)).collect(Collectors.toList()));

	}
	
	/**
	 * Wraps a Pokemon into a Pokemon VO.
	 * @param pokemon the pokemon to wrap
	 * @return the pokemon VO to return on the Controller
	 */
	private PokemonVO getPokemonVO(Pokemon pokemon) {
		return pokemon != null ? PokemonVO.builder().pokemon(pokemon).build() : PokemonVO.builder().build();
	}
	
	

}
