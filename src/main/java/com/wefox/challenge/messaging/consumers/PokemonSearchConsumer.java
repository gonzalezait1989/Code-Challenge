package com.wefox.challenge.messaging.consumers;

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

import com.wefox.challenge.messaging.topic.PokemonSearchTopic;
import com.wefox.challenge.service.PokemonService;
import com.wefox.challenge.vo.PokemonVO;

@Component
@EnableBinding(PokemonSearchTopic.class)
@EnableAutoConfiguration
public class PokemonSearchConsumer {
	
	@Autowired
	private PokemonService pokemonService;
	
	
    @StreamListener(PokemonSearchTopic.INPUT)
    @SendTo(PokemonSearchTopic.RESULT_OUTPUT)
    public Message<List<PokemonVO>> receive(String payload) {
    	Optional<List<PokemonVO>> pokemons = pokemonService.findByName(payload);
    	if(pokemons.isPresent()) {
    		Message<List<PokemonVO>> message = MessageBuilder.withPayload(pokemons.get()).build();
    		return message;
    	}
    	return null;
    }
}
