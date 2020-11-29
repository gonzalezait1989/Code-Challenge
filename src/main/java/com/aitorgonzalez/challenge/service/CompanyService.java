package com.aitorgonzalez.challenge.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.model.Company;
import com.aitorgonzalez.challenge.vo.AccountVO;
import com.aitorgonzalez.challenge.vo.CompanyVO;

public interface CompanyService {

	/**
	 * Gets all the Companies.
	 * 
	 * @return a list of Companies.
	 */
	public List<CompanyVO> findAll();
	
	/**
	 * Transform Company to CompanyVO
	 * 
	 * @param companyVO
	 * @return
	 */
	default Company getCompany(CompanyVO companyVO) {
		if (companyVO == null)
			return Company.builder().build();
		List<Account> accounts;
		if (companyVO.getAccounts() == null || companyVO.getAccounts().isEmpty()) {
			accounts = Collections.emptyList();
		} else {
			accounts = companyVO.getAccounts().parallelStream().map(this::getAccount).collect(Collectors.toList());
		}
		return Company.builder().id(companyVO.getId()).name(companyVO.getName()).created(companyVO.getCreated()).updated(companyVO.getUpdated())
				.accounts(accounts)
				.build();
	}

	/**
	 * Transform Company to CompanyVO
	 * 
	 * @param company
	 * @return
	 */
	default CompanyVO getCompanyVO(Company company) {
		if (company == null)
			return CompanyVO.builder().build();
		List<AccountVO> accounts;
		if (company.getAccounts() == null || company.getAccounts().isEmpty()) {
			accounts = Collections.emptyList();
		} else {
			accounts = company.getAccounts().parallelStream().map(this::getAccountVO).collect(Collectors.toList());
		}
		return CompanyVO.builder().id(company.getId()).name(company.getName()).created(company.getCreated()).updated(company.getUpdated())
				.accounts(accounts)
				.build();
	}

	/**
	 * Gets an Account from an AccountVO
	 * 
	 * @param accountVO the account to transform
	 * @return the Account to return
	 */
	Account getAccount(AccountVO accountVO);

	/**
	 * Gets an AddressVO from an Address
	 * 
	 * @param address the address to transform
	 * @return the AddressVO to return
	 */
	AccountVO getAccountVO(Account address);

	/**
	 * Saves one Company with it's Accounts (if any)
	 * 
	 * @param companyVO the account to persist.
	 * @return the persisted company.
	 */
	public CompanyVO save(CompanyVO companyVO);

	/**
	 * Finds an CompanyVO by name.
	 * 
	 * @param name the name of the CompanyVO.
	 * @return an Optional of the CompanyVO.
	 */
	public Optional<CompanyVO> findByName(String name);

	/**
	 * Finds an CompanyVO by id.
	 * 
	 * @param id the id of the CompanyVO.
	 * @return an Optional of the CompanyVO.
	 */
	public Optional<CompanyVO> findById(Long id);

	/**
	 * Deletes an Company by it's Id.
	 * 
	 * @param id the id of the Company.
	 */
	public void deleteById(Long id);
}
