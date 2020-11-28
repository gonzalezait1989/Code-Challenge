package com.aitorgonzalez.challenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aitorgonzalez.challenge.model.Toy;

/**
 * Repository to perform CRUD operations over Toy entities.
 * 
 * @author aitor
 */
@Repository
public interface ToyRepository extends CrudRepository<Toy, Long> {

}
