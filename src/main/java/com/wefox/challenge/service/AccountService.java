package com.wefox.challenge.service;

import java.util.List;
import java.util.Optional;

import com.wefox.challenge.vo.AccountVO;

/**
 * Service to manage Accounts.
 * 
 * @author aitor
 */
public interface AccountService {

  /**
   * Gets all the Accounts.
   * 
   * @return a list of Accounts.
   */
  public List<AccountVO> findAll();

  /**
   * Saves one Account with it's Addresses (if any)
   * 
   * @param accountVO the account to persist.
   * @return the persisted account.
   */
  public AccountVO save(AccountVO accountVO);

  /**
   * Finds an AccountVO by ID.
   * 
   * @param id the Id of the Account VO.
   * @return an Optional of the AccountVO.
   */
  public Optional<AccountVO> findById(Long id);

  /**
   * Finds an AccountVO by Email.
   * 
   * @param email the Email of the Account VO.
   * @return an Optional of the AccountVO.
   */
  public Optional<AccountVO> findByEmail(String email);

  /**
   * Checks if an Account exists by it's Id.
   * 
   * @param id the Id of the Account.
   * @return true if exists, false otherwise.
   */
  public boolean existsById(Long id);

  /**
   * Deletes an Account by it's Id.
   * 
   * @param id the id of the Account.
   */
  public void deleteById(Long id);

}
