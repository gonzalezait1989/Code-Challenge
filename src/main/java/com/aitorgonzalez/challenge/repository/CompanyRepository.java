package com.aitorgonzalez.challenge.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aitorgonzalez.challenge.model.Company;
import com.aitorgonzalez.challenge.vo.CompanyVO;

public interface CompanyRepository extends CrudRepository<Company, Long>  {

	Optional<CompanyVO> findByName(String name);

}
