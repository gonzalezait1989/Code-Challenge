package com.aitorgonzalez.challenge.repository;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aitorgonzalez.challenge.model.Account;

/**
 * Repository to perform CRUD operations over Account entities.
 * 
 * @author aitor
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * Finds an account by the email associated to the account.
	 * 
	 * @param email the email associated to the account.
	 * @return the Account.
	 */
	Optional<Account> findByEmail(@Email String email);

}
