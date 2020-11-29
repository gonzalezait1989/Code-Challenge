package com.aitorgonzalez.challenge.factory;

import org.apache.commons.pool2.impl.GenericObjectPool;

import lombok.extern.slf4j.Slf4j;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

@Slf4j
public class PokemonApiClientPoolFactory extends GenericObjectPool<PokeApiClient> {

	private static PokemonApiClientPoolFactory poolFactory;

	public static PokemonApiClientPoolFactory poolFactory() {
		if (PokemonApiClientPoolFactory.poolFactory == null) {
			PokemonApiClientPoolFactory.poolFactory = new PokemonApiClientPoolFactory(new PokemonApiClientFactory());
		}

		return PokemonApiClientPoolFactory.poolFactory;
	}

	private PokemonApiClientPoolFactory(PokemonApiClientFactory factory) {
		super(factory);
		this.setMaxTotal(-1);
		this.setTestOnBorrow(true);
		this.setMaxIdle(-1);
		this.setMaxWaitMillis(100);
	}
	
	public static void logPool() {
		log.info("counting PokemonApi clients active [{}] idle [{}]", PokemonApiClientPoolFactory.poolFactory().getNumActive(),
				PokemonApiClientPoolFactory.poolFactory().getNumIdle());
	}

}
