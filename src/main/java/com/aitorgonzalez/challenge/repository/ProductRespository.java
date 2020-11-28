package com.aitorgonzalez.challenge.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aitorgonzalez.challenge.model.Product;

@Repository
public interface ProductRespository extends CrudRepository<Product, Long> {
	Optional<Product> findByName(String name);
}
