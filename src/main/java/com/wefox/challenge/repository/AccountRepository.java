package com.wefox.challenge.repository;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wefox.challenge.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  Optional<Account> findByEmail(@Email String email);

}
