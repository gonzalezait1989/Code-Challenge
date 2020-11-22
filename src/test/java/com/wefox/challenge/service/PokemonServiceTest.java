package com.wefox.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.integration.support.MessageBuilder;

import com.wefox.challenge.messaging.producers.PokemonMessageProducer;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonAbility;
import me.sargunvohra.lib.pokekotlin.model.PokemonHeldItem;
import me.sargunvohra.lib.pokekotlin.model.PokemonMove;
import me.sargunvohra.lib.pokekotlin.model.PokemonSprites;
import me.sargunvohra.lib.pokekotlin.model.PokemonStat;
import me.sargunvohra.lib.pokekotlin.model.PokemonType;
import me.sargunvohra.lib.pokekotlin.model.VersionGameIndex;

@RunWith(MockitoJUnitRunner.class)

public class PokemonServiceTest {

	@InjectMocks
	private PokemonService pokemonService;
	
	@Mock
	private PokemonMessageProducer pokemonMessageProducer;
	
	PokeApiClient pokeApiClient = Mockito.mock(PokeApiClient.class);
	
	@Before
	public void prepareDataSet() {
		String next = "\"https://pokeapi.co/api/v2/pokemon?offset=200&limit=100\"";
		String previous = "\"https://pokeapi.co/api/v2/pokemon?offset=0&limit=100\"";		
		int count = 12;
		List<NamedApiResource> results = new ArrayList<NamedApiResource>();
		
		results.add(new NamedApiResource("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/", 1));
		results.add(new NamedApiResource("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/", 2));
		results.add(new NamedApiResource("venusaur", "https://pokeapi.co/api/v2/pokemon/3/", 3));
		results.add(new NamedApiResource("charmander", "https://pokeapi.co/api/v2/pokemon/4/", 4));
		results.add(new NamedApiResource("charmeleon", "https://pokeapi.co/api/v2/pokemon/5/", 5));
		results.add(new NamedApiResource("charizard", "https://pokeapi.co/api/v2/pokemon/6/", 6));
		results.add(new NamedApiResource("squirtle", "https://pokeapi.co/api/v2/pokemon/7/", 7));
		results.add(new NamedApiResource("wartortle", "https://pokeapi.co/api/v2/pokemon/8/", 8));
		results.add(new NamedApiResource("blastoise", "https://pokeapi.co/api/v2/pokemon/9/", 9));
		results.add(new NamedApiResource("caterpie", "https://pokeapi.co/api/v2/pokemon/10/", 10));
		results.add(new NamedApiResource("metapod", "https://pokeapi.co/api/v2/pokemon/11/", 11));
		results.add(new NamedApiResource("butterfree", "https://pokeapi.co/api/v2/pokemon/12/", 12));
		
		List<PokemonAbility> pokemonAbilities = new ArrayList<PokemonAbility>();
		List<VersionGameIndex> gameIndices = new ArrayList<VersionGameIndex>();
        List<PokemonHeldItem> heldItems = new ArrayList<PokemonHeldItem>();
        List<PokemonMove> moves = new ArrayList<PokemonMove>();
        List<PokemonStat> stats = new ArrayList<PokemonStat>();
        List<PokemonType> types = new ArrayList<PokemonType>();
        PokemonSprites sprites = new PokemonSprites("", "", "", "", "", "", "", "");
		Pokemon bulbasaur = new Pokemon(1, "bulbasaur", count, count, false, count, count, new NamedApiResource("", "", 1), pokemonAbilities, results, gameIndices, heldItems, moves, stats, types, sprites);
		Pokemon charmander = new Pokemon(4, "charmander",count, count, false, count, count, new NamedApiResource("", "", 1), pokemonAbilities, results, gameIndices, heldItems, moves, stats, types, sprites);
		Pokemon charmeleon = new Pokemon(5, "charmeleon", count, count, false, count, count, new NamedApiResource("", "", 1), pokemonAbilities, results, gameIndices, heldItems, moves, stats, types, sprites);
		Pokemon charizard = new Pokemon(6, "charizard", count, count, false, count, count, new NamedApiResource("", "", 1), pokemonAbilities, results, gameIndices, heldItems, moves, stats, types, sprites);
		Pokemon blastoise = new Pokemon(9, "blastoise", count, count, false, count, count, new NamedApiResource("", "", 1), pokemonAbilities, results, gameIndices, heldItems, moves, stats, types, sprites);
		Pokemon butterfree = new Pokemon(12, "butterfree", count, count, false, count, count, new NamedApiResource("", "", 1), pokemonAbilities, results, gameIndices, heldItems, moves, stats, types, sprites);
		
		NamedApiResourceList namedResourceApiList = new NamedApiResourceList(count, next, previous, results);
		when(pokeApiClient.getPokemonList(0, 100)).thenReturn(namedResourceApiList);
		when(pokeApiClient.getPokemon(1)).thenReturn(bulbasaur);
		when(pokeApiClient.getPokemon(4)).thenReturn(charmander);
		when(pokeApiClient.getPokemon(5)).thenReturn(charmeleon);
		when(pokeApiClient.getPokemon(6)).thenReturn(charizard);
		when(pokeApiClient.getPokemon(9)).thenReturn(blastoise);
		when(pokeApiClient.getPokemon(12)).thenReturn(butterfree);
		
		when(pokemonMessageProducer.produceFindByName(null)).thenReturn(null);
		when(pokemonMessageProducer.produceFindByName("")).thenReturn(null);
		when(pokemonMessageProducer.produceFindByName(" ")).thenReturn(null);
		when(pokemonMessageProducer.produceFindByName("aitor")).thenReturn(MessageBuilder.withPayload("aitor").build());
		when(pokemonMessageProducer.produceFindByName("bulbasaur")).thenReturn(MessageBuilder.withPayload("bulbasaur").build());

	}
	
	@Test
	public void testFindByName() throws Exception {
		assertThat(pokemonService.findByName("")).isNotNull();
		assertThat(pokemonService.findByName("")).isEmpty();
		assertThat(pokemonService.findByName(" ")).isNotNull();
		assertThat(pokemonService.findByName(" ")).isEmpty();
		assertThat(pokemonService.findByName("x")).isNotNull();
		assertThat(pokemonService.findByName("x")).isEmpty();
		assertThat(pokemonService.findByName("aitor")).isNotNull();
		assertThat(pokemonService.findByName("aitor")).isEmpty();
		assertThat(pokemonService.findByName("bulbasaur")).isNotNull();
		assertThat(pokemonService.findByName("bulbasaur")).isNotEmpty();
		assertThat(pokemonService.findByName("bulbasaur").get()).hasSize(1);
		assertThat(pokemonService.findByName("BuLbAsAuR")).isNotNull();
		assertThat(pokemonService.findByName("BuLbAsAuR")).isNotEmpty();
		assertThat(pokemonService.findByName("BuLbAsAuR").get()).hasSize(1);
		assertThat(pokemonService.findByName("char")).isNotNull();
		assertThat(pokemonService.findByName("char")).isNotEmpty();
		assertThat(pokemonService.findByName("char").get()).hasSize(3);
		assertThat(pokemonService.findByName("cHar")).isNotNull();
		assertThat(pokemonService.findByName("cHar")).isNotEmpty();
		assertThat(pokemonService.findByName("cHar").get()).hasSize(3);
		assertThat(pokemonService.findByName("b")).isNotNull();
		assertThat(pokemonService.findByName("b")).isNotEmpty();
		assertThat(pokemonService.findByName("b").get()).hasSize(3);	
	}
	
	@Test
	public void testFindByNameAsync() {
		assertThat(pokemonService.findByNameAsync(null)).isNotNull();
		assertThat(pokemonService.findByNameAsync(null)).isEmpty();
		assertThat(pokemonService.findByNameAsync("")).isNotNull();
		assertThat(pokemonService.findByNameAsync("")).isEmpty();
		assertThat(pokemonService.findByNameAsync(" ")).isNotNull();
		assertThat(pokemonService.findByNameAsync(" ")).isEmpty();
		assertThat(pokemonService.findByNameAsync("aitor")).isNotNull();
		assertThat(pokemonService.findByNameAsync("aitor")).isNotEmpty();
		assertThat(pokemonService.findByNameAsync("aitor").get().getPayload()).isEqualTo("aitor");
		assertThat(pokemonService.findByNameAsync("bulbasaur")).isNotNull();
		assertThat(pokemonService.findByNameAsync("bulbasaur")).isNotEmpty();
		assertThat(pokemonService.findByNameAsync("bulbasaur").get().getPayload()).isEqualTo("bulbasaur");
	}

}
