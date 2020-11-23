package com.wefox.challenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wefox.challenge.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
