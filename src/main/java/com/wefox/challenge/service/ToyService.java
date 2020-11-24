package com.wefox.challenge.service;

import java.util.Optional;

import com.wefox.challenge.vo.ToyVO;

/**
 * Service to manage Toys.
 * 
 * @author aitor
 *
 */
public interface ToyService {

  /**
   * Finds a ToyVO by it's Id.
   * 
   * @param id the Id of the Toy.
   * @return an Optional of the ToyVO
   */
  public Optional<ToyVO> findById(Long id);

}
