package com.wefox.challenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wefox.challenge.model.Product;

@Repository
public interface ProductRespository extends CrudRepository<Product, Long> {
}
