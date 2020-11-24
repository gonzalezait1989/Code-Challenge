package com.wefox.challenge.messaging.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.wefox.challenge.messaging.topic.PokemonSearchTopic;

/**
 * Publishes messages based on the Pokemon search requests from the REST
 * Endpoint.
 * 
 * @author aitor
 */
@Component
@EnableAutoConfiguration
public class PokemonMessageProducer {

  /**
   * The Topic definitions.
   */
  @Autowired
  private PokemonSearchTopic pokemonSearchTopic;

  /**
   * Publishes messages based on the Pokemon search requests from the REST
   * Endpoint.
   * 
   * @param name the Pokemon name.
   * @return the published message.
   */
  public Message<String> produceFindByName(String name) {
    if (name == null || name.replace(" ","").isEmpty())
      return null;

    Message<String> message = MessageBuilder.withPayload(name).build();
    if (this.pokemonSearchTopic.output().send(message)) {
      return message;
    }
    return null;
  }
}
