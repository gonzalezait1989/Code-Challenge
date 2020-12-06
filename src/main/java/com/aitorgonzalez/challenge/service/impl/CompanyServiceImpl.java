package com.aitorgonzalez.challenge.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.model.Address;
import com.aitorgonzalez.challenge.model.Company;
import com.aitorgonzalez.challenge.repository.CompanyRepository;
import com.aitorgonzalez.challenge.service.AccountService;
import com.aitorgonzalez.challenge.service.CompanyService;
import com.aitorgonzalez.challenge.vo.AccountVO;
import com.aitorgonzalez.challenge.vo.CompanyVO;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public void deleteById(Long id) {
		this.companyRepository.deleteById(id);
	}

	@Override
	public List<CompanyVO> findAll() {
		List<CompanyVO> companies = new ArrayList<>();
		this.companyRepository.findAll().iterator().forEachRemaining(c -> companies.add(getCompanyVO(c)));
		return companies;
	}

	@Override
	public Optional<CompanyVO> findById(Long id) {
		return this.companyRepository.findById(id).map(this::getCompanyVO);
	}

	@Override
	public Optional<CompanyVO> findByName(String name) {
		return this.companyRepository.findByName(name).map(this::getCompanyVO);
	}

	@Override
	public Account getAccount(AccountVO accountVO) {
		return accountService.getAccount(accountVO);
	}

	@Override
	public AccountVO getAccountVO(Account address) {
		return accountService.getAccountVO(address);
	}

	@Override
	public CompanyVO save(CompanyVO companyVO) {
		Company company = this.getCompany(companyVO);
		if (company.getAccounts() != null && !company.getAccounts().isEmpty()) {
			List<Account> updatedAccounts = company.getAccounts().stream().map(account -> {
				account.setCompany(company);
				List<Address> updatedAddresses = Collections.emptyList();
				if (account.getAddresses() != null && !account.getAddresses().isEmpty()) {
					updatedAddresses = account.getAddresses().parallelStream()
							.map(address -> {
								address.setAccount(account);
								return address;	
							})
							.collect(Collectors.toList());
				}
				account.setAddresses(updatedAddresses);
				return account;
			}).collect(Collectors.toList());
			company.setAccounts(updatedAccounts);
		}
		return this.getCompanyVO(this.companyRepository.save(company));
	}

	@Override
	public boolean existsById(Long id) {
		return this.companyRepository.existsById(id);
	}
}
