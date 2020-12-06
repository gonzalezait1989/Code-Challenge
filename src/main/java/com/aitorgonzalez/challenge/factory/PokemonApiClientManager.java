package com.aitorgonzalez.challenge.factory;

import lombok.extern.slf4j.Slf4j;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

@Slf4j
public class PokemonApiClientManager {


	public static void destroyAll() {
		PokemonApiClientPoolFactory.poolFactory().clear();
	}
	
	public static void destroyPokemonApiClient(PokeApiClient pokeApiClient) {
		try {
			log.debug("force destroy PokemonApi client [{}]", pokeApiClient.hashCode());
			PokemonApiClientPoolFactory.poolFactory().invalidateObject(pokeApiClient);
			PokemonApiClientPoolFactory.logPool();

		} catch (Exception ex) {
			log.error("Exception when destroying PokemonApiClient client", ex);
		}
	}

	public static PokeApiClient getPokemonApiClient() {

		try {
			PokeApiClient pokeApiClient = PokemonApiClientPoolFactory.poolFactory().borrowObject();
			log.debug("getting PokemonApi client[{}]", pokeApiClient.hashCode());
			PokemonApiClientPoolFactory.logPool();
			return pokeApiClient;

		} catch (Exception ex) {
			log.error("Exception when getting PokemonApi client", ex);
			return null;
		}
	}

	public static void releasePokemonApiClient(PokeApiClient pokeApiClient) {
		log.debug("releasing PokemonApi client [{}]", pokeApiClient.hashCode());
		try {

			PokemonApiClientPoolFactory.poolFactory().returnObject(pokeApiClient);
			PokemonApiClientPoolFactory.logPool();

		} catch (IllegalStateException ex) {
			if (ex.getMessage().equals("Object has already been returned to this pool or is invalid")) {
				// just ignore, the object was already free
				return;
			}
			log.error("exception when releasing PokemonApi client", ex);
		}
	}

	private PokemonApiClientManager() {
		
	}
}
