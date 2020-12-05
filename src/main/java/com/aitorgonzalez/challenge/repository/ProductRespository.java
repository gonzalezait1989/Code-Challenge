package com.aitorgonzalez.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aitorgonzalez.challenge.model.Product;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {
	Optional<Product> findByName(String name);
}
