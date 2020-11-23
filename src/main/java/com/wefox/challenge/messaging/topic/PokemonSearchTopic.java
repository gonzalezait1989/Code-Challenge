package com.wefox.challenge.messaging.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PokemonSearchTopic {

  String INPUT = "pokemon-search-topic-input";
  String OUTPUT = "pokemon-search-topic-output";
  String RESULT_OUTPUT = "pokemon-result-topic-output";

  @Output(OUTPUT)
  MessageChannel output();

  @Output(RESULT_OUTPUT)
  MessageChannel resultOutput();

  @Input(INPUT)
  SubscribableChannel input();
}
