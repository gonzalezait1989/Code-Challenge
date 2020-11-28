package com.aitorgonzalez.challenge.service;

import java.util.Optional;

import com.aitorgonzalez.challenge.model.Toy;
import com.aitorgonzalez.challenge.vo.ToyVO;

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

	/**
	 * Transform Toy to ToyVO
	 * 
	 * @param toy
	 * @return
	 */
	default ToyVO getToyVO(Toy toy) {
		return toy != null
				? ToyVO.builder().created(toy.getCreated()).updated(toy.getUpdated()).id(toy.getId())
						.name(toy.getName()).build()
				: ToyVO.builder().build();
	}

}
