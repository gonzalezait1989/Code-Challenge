package com.wefox.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wefox.challenge.model.Toy;

public interface ToyRepository  extends JpaRepository<Toy, Long> {

}
