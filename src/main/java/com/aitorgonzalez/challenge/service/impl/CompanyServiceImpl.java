package com.aitorgonzalez.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.model.Company;
import com.aitorgonzalez.challenge.repository.CompanyRepository;
import com.aitorgonzalez.challenge.service.AccountService;
import com.aitorgonzalez.challenge.service.CompanyService;
import com.aitorgonzalez.challenge.vo.AccountVO;
import com.aitorgonzalez.challenge.vo.CompanyVO;

public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private AccountService accountService;

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
		return this.companyRepository.findByName(name);
	}
	
	@Override
	public CompanyVO save(CompanyVO companyVO) {
		Company company = this.getCompany(companyVO);
		if (company.getAccounts() != null && !company.getAccounts().isEmpty()) {
			company.getAccounts().parallelStream().forEach(account -> account.setCompany(company));
		}
		return this.getCompanyVO(this.companyRepository.save(company));
	}
	
	@Override
	public void deleteById(Long id) {
		this.companyRepository.deleteById(id);
	}

	@Override
	public Account getAccount(AccountVO accountVO) {
		return accountService.getAccount(accountVO);
	}

	@Override
	public AccountVO getAccountVO(Account address) {
		return accountService.getAccountVO(address);
	}
}
