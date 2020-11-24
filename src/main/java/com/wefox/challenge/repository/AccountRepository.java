package com.wefox.challenge.repository;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wefox.challenge.model.Account;

/**
 * Repository to perform CRUD operations over Account entities.
 * 
 * @author aitor
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  /**
   * Finds an account by the email associated to the account.
   * 
   * @param email the email associated to the account.
   * @return the Account.
   */
  Optional<Account> findByEmail(@Email String email);

}
