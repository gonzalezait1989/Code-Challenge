package com.wefox.challenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wefox.challenge.model.Toy;

/**
 * Repository to perform CRUD operations over Toy entities.
 * 
 * @author aitor
 */
@Repository
public interface ToyRepository extends CrudRepository<Toy, Long> {

}
