package com.aitorgonzalez.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.aitorgonzalez.challenge.factory.PokemonApiClientManager;
import com.aitorgonzalez.challenge.messaging.producers.PokemonMessageProducer;
import com.aitorgonzalez.challenge.service.PokemonService;
import com.aitorgonzalez.challenge.vo.PokemonVO;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

@Service
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokemonMessageProducer pokemonMessageProducer;

	/**
	 * Finds pokemons by name.
	 * 
	 * @param name the name of the pokemon.
	 * @return an optional List of Pokemons if found.
	 */
	@Override
	public Optional<List<PokemonVO>> findByName(String name) {
		if (name == null || name.replace(" ", "").isEmpty()) {
			return Optional.empty();
		}
		
		//We get a client from a pool to make sure that we use a different client on each call. 
		PokeApiClient pokeApiClient = PokemonApiClientManager.getPokemonApiClient();
		int elements = 100;
		int offset = 0;
		List<Pokemon> pokemons = new ArrayList<>();
		// There are 1050 pokemons, but I do it in batches of 100 to make sure that if
		// some more are added, we don't miss them.
		while (elements == 100) {
			// A list of referenced resources. I am fetching all the resources this way
			// because the API does not provide an endpoint to search by proximations of the
			// name (startswith),
			// just the full name
			NamedApiResourceList pokemonList = pokeApiClient.getPokemonList(offset, elements);
			pokemonList.component4().parallelStream().forEach(resource -> {
				if (resource.getName().toLowerCase().startsWith(name.toLowerCase())) {
					pokemons.add(pokeApiClient.getPokemon(resource.getId()));
				}
			});
			// When we had less than 100 elements, this will be the end of the looping
			elements = pokemonList.getResults().size();
			offset += pokemonList.getResults().size();
		}
		//Releasing the Pokemon API client to be reused in the future.
		PokemonApiClientManager.releasePokemonApiClient(pokeApiClient);
		if (pokemons.isEmpty())
			return Optional.empty();
		return Optional.of(pokemons.parallelStream().map(this::getPokemonVO).collect(Collectors.toList()));

	}

	/**
	 * Calls the Message Producer to create a new request to search for Pokemons.
	 * 
	 * @param name the name of the Pokemon.
	 * @return the generated message.
	 */
	@Override
	public Optional<Message<String>> findByNameAsync(String name) {
		Message<String> message = this.pokemonMessageProducer.produceFindByName(name);
		if (message == null) {
			return Optional.empty();
		}
		return Optional.of(message);
	}
}
