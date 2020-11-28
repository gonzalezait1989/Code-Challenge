package com.aitorgonzalez.challenge.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.vo.AccountVO;

/**
 * Service to manage Accounts.
 * 
 * @author aitor
 */
public interface AccountService {

	/**
	 * Deletes an Account by it's Id.
	 * 
	 * @param id the id of the Account.
	 */
	public void deleteById(Long id);

	/**
	 * Checks if an Account exists by it's Id.
	 * 
	 * @param id the Id of the Account.
	 * @return true if exists, false otherwise.
	 */
	public boolean existsById(Long id);

	/**
	 * Gets all the Accounts.
	 * 
	 * @return a list of Accounts.
	 */
	public List<AccountVO> findAll();

	/**
	 * Finds an AccountVO by Email.
	 * 
	 * @param email the Email of the Account VO.
	 * @return an Optional of the AccountVO.
	 */
	public Optional<AccountVO> findByEmail(String email);

	/**
	 * Finds an AccountVO by ID.
	 * 
	 * @param id the Id of the Account VO.
	 * @return an Optional of the AccountVO.
	 */
	public Optional<AccountVO> findById(Long id);

	/**
	 * Transform Account to AccountVO
	 * 
	 * @param accountVO
	 * @return
	 */
	default Account getAccount(AccountVO accountVO) {
		return accountVO != null
				? Account.builder().id(accountVO.getId()).created(accountVO.getCreated())
						.updated(accountVO.getUpdated()).age(
								accountVO.getAge())
						.email(accountVO.getEmail()).name(accountVO.getName())
						.addresses(accountVO.getAddresses() != null ? accountVO.getAddresses().parallelStream()
								.map(addressVO -> AddressService.getAddress(addressVO)).collect(Collectors.toList())
								: Collections.emptyList())
						.build()
				: Account.builder().build();
	}

	/**
	 * Transform Account to AccountVO
	 * 
	 * @param account
	 * @return
	 */
	default AccountVO getAccountVO(Account account) {
		return account != null ? AccountVO.builder().created(account.getCreated()).updated(account.getUpdated())
				.id(account.getId()).age(account.getAge()).email(account.getEmail()).name(account.getName())
				.addresses(account.getAddresses() != null ? account.getAddresses().parallelStream()
						.map(address -> AddressService.getAddressVO(address)).collect(Collectors.toList())
						: Collections.emptyList())
				.build() : AccountVO.builder().build();
	}

	/**
	 * Saves one Account with it's Addresses (if any)
	 * 
	 * @param accountVO the account to persist.
	 * @return the persisted account.
	 */
	public AccountVO save(AccountVO accountVO);

}
