package com.wefox.challenge.messaging.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.wefox.challenge.messaging.topic.PokemonSearchTopic;

@Component
@EnableAutoConfiguration
public class PokemonMessageProducer {

  @Autowired
  private PokemonSearchTopic pokemonSearchTopic;

  public Message<String> produceFindByName(String name) {
    if (name == null || name.isBlank())
      return null;

    Message<String> message = MessageBuilder.withPayload(name).build();
    if (this.pokemonSearchTopic.output().send(message)) {
      return message;
    }
    return null;
  }
}
