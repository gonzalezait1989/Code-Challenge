package com.wefox.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wefox.challenge.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
