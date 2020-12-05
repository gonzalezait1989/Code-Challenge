package com.aitorgonzalez.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aitorgonzalez.challenge.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>  {

	Optional<Company> findByName(String name);

}
