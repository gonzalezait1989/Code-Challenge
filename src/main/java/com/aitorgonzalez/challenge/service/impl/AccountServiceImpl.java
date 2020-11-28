package com.aitorgonzalez.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.repository.AccountRepository;
import com.aitorgonzalez.challenge.service.AccountService;
import com.aitorgonzalez.challenge.vo.AccountVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRespository;

	@Override
	public void deleteById(Long id) {
		this.accountRespository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return this.accountRespository.existsById(id);
	}

	@Override
	public List<AccountVO> findAll() {
		List<AccountVO> accounts = new ArrayList<AccountVO>();
		accountRespository.findAll().iterator().forEachRemaining(a -> accounts.add(getAccountVO(a)));
		return accounts;
	}

	@Override
	public Optional<AccountVO> findByEmail(@Email String email) {
		return this.accountRespository.findByEmail(email).map(a -> getAccountVO(a));
	}

	@Override
	public Optional<AccountVO> findById(Long id) {
		return this.accountRespository.findById(id).map(a -> getAccountVO(a));
	}

	@Override
	public AccountVO save(@Valid AccountVO accountVO) {
		Account account = this.getAccount(accountVO);
		if (account.getAddresses() != null && !account.getAddresses().isEmpty()) {
			account.getAddresses().parallelStream().forEach(address -> address.setAccount(account));
		}
		return this.getAccountVO(this.accountRespository.save(account));
	}
}
