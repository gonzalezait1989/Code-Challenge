package com.wefox.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.messaging.Message;

import com.wefox.challenge.vo.PokemonVO;

/**
 * Service to manage Pokemons.
 * 
 * @author aitor
 */
public interface PokemonService {

  public Optional<List<PokemonVO>> findByName(String name);

  /**
   * Calls the Message Producer to create a new request to search for Pokemons.
   * 
   * @param name the name of the Pokemon.
   * @return the generated message.
   */
  public Optional<Message<String>> findByNameAsync(String name);
}
