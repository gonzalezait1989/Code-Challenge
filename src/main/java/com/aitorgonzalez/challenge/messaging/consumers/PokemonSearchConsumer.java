package com.aitorgonzalez.challenge.messaging.consumers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.aitorgonzalez.challenge.messaging.topic.PokemonSearchTopic;
import com.aitorgonzalez.challenge.service.PokemonService;
import com.aitorgonzalez.challenge.vo.PokemonVO;

/**
 * Consumer that consumes the messages from the search request topic, searches
 * for the pokemon with that name and publishes the result on another queue.
 * 
 * @author aitor
 */
@Component
@EnableBinding(PokemonSearchTopic.class)
@EnableAutoConfiguration
public class PokemonSearchConsumer {

	/**
	 * The Pokemon service used to search for Pokemons by name.
	 */
	@Autowired
	private PokemonService pokemonService;

	/**
	 * The consumer mapping. Consumes the content of the messages and publishes
	 * messages on another topic with the results.
	 * 
	 * @param payload the search request message content.
	 * @return the Message with the results of the search.
	 */
	@StreamListener(PokemonSearchTopic.INPUT)
	@SendTo(PokemonSearchTopic.RESULT_OUTPUT)
	public Message<List<PokemonVO>> receive(String payload) {
		Optional<List<PokemonVO>> pokemons = pokemonService.findByName(payload);
		if (pokemons.isPresent()) {
			return MessageBuilder.withPayload(pokemons.get()).build();
		}
		return null;
	}
}
