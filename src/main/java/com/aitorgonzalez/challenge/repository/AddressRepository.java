package com.aitorgonzalez.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aitorgonzalez.challenge.model.Address;

/**
 * Repository to perform CRUD operations over Address entities.
 * 
 * @author aitor
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
