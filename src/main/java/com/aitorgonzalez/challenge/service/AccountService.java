package com.aitorgonzalez.challenge.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.model.Address;
import com.aitorgonzalez.challenge.vo.AccountVO;
import com.aitorgonzalez.challenge.vo.AddressVO;

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
		if (accountVO == null)
			return Account.builder().build();
		List<Address> addresses;
		if (accountVO.getAddresses() == null || accountVO.getAddresses().isEmpty()) {
			addresses = Collections.emptyList();
		} else {
			addresses = accountVO.getAddresses().parallelStream().map(this::getAddress).collect(Collectors.toList());
		}
		return Account.builder().id(accountVO.getId()).created(accountVO.getCreated()).updated(accountVO.getUpdated())
				.age(accountVO.getAge()).email(accountVO.getEmail()).name(accountVO.getName()).addresses(addresses)
				.build();
	}

	/**
	 * Transform Account to AccountVO
	 * 
	 * @param account
	 * @return
	 */
	default AccountVO getAccountVO(Account account) {
		if (account == null)
			return AccountVO.builder().build();
		List<AddressVO> addresses;
		if (account.getAddresses() == null || account.getAddresses().isEmpty()) {
			addresses = Collections.emptyList();
		} else {
			addresses = account.getAddresses().parallelStream().map(this::getAddressVO).collect(Collectors.toList());
		}
		return AccountVO.builder().created(account.getCreated()).updated(account.getUpdated()).id(account.getId())
				.age(account.getAge()).email(account.getEmail()).name(account.getName()).addresses(addresses).build();
	}

	/**
	 * Gets an Address from an AddressVO
	 * 
	 * @param address the address to transform
	 * @return the Address to return
	 */
	Address getAddress(AddressVO addressVO);

	/**
	 * Gets an AddressVO from an Address
	 * 
	 * @param address the address to transform
	 * @return the AddressVO to return
	 */
	AddressVO getAddressVO(Address address);

	/**
	 * Saves one Account with it's Addresses (if any)
	 * 
	 * @param accountVO the account to persist.
	 * @return the persisted account.
	 */
	public AccountVO save(AccountVO accountVO);

}
