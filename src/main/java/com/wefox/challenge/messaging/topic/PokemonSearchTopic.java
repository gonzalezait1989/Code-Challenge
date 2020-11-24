package com.wefox.challenge.messaging.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Interface to manage the different Pokemon topics.
 * 
 * @author aitor
 */
public interface PokemonSearchTopic {

  String INPUT = "pokemon-search-topic-input";
  String OUTPUT = "pokemon-search-topic-output";
  String RESULT_OUTPUT = "pokemon-result-topic-output";

  /**
   * Defines a topic where the search messages will be publiched.
   * 
   * @return a Subscribable channel.
   */
  @Output(OUTPUT)
  MessageChannel output();

  /**
   * Defines a topic where the result messages after obtaining the Pokemons will
   * be published
   * 
   * @return a Subscribable channel.
   */
  @Output(RESULT_OUTPUT)
  MessageChannel resultOutput();

  /**
   * Defines a topic where the search messages will be consumed from.
   * 
   * @return a Subscribable channel.
   */
  @Input(INPUT)
  SubscribableChannel input();
}
