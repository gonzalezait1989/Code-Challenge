package com.aitorgonzalez.challenge.factory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class PokemonApiClientFactory extends BasePooledObjectFactory<PokeApiClient> {

	@Override
	public PokeApiClient create() throws Exception {
		return new PokeApiClient();
	}

	@Override
	public PooledObject<PokeApiClient> wrap(PokeApiClient obj) {
		return new DefaultPooledObject<>(obj);
	}
}
