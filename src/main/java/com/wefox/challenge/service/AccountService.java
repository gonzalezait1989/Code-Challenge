package com.wefox.challenge.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wefox.challenge.model.Account;
import com.wefox.challenge.repository.AccountRepository;
import com.wefox.challenge.vo.AccountVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	@Autowired
    private  AccountRepository accountRespository;
	@Autowired
	private AddressService addressService;

	public List<AccountVO> findAll() {
		return accountRespository.findAll().stream()
        .map(a -> getAccountVO(a))
        .collect(Collectors.toList());
	}
	
	
	public AccountVO save(@Valid AccountVO accountVO) {
		return this.getAccountVO(this.accountRespository.save(this.getAccount(accountVO)));
	}
	
	public Optional<AccountVO> findById(Long id) {
		return this.accountRespository.findById(id).map(a -> getAccountVO(a));
	}
	
	public Optional<AccountVO> findByEmail(@Email String email) {
		return this.accountRespository.findByEmail(email).map(a -> getAccountVO(a));
	}
	
	public boolean existsById(Long id) {
		return this.accountRespository.existsById(id);
	}
	
	public void deleteById(Long id) {
		this.accountRespository.deleteById(id);
	}
	
	/**
     * Transform Account to AccountVO
     * @param account
     * @return
     */
    private AccountVO getAccountVO(Account account) {
        return account  != null ? AccountVO.builder()
                .created(account.getCreated())
                .updated(account.getUpdated())
                .id(account.getId())
                .age(account.getAge())
                .email(account.getEmail())
                .name(account.getName())
                .addresses(account.getAddresses() != null ? account.getAddresses().parallelStream().map(address ->  addressService.getAddressVO(address))
                		.collect(Collectors.toList()) : Collections.emptyList())
                .build() : AccountVO.builder().build();
    }

    /**
     * Transform Account to AccountVO
     * @param accountVO
     * @return
     */
    private Account getAccount(AccountVO accountVO) {
    	return accountVO != null ? Account.builder()
                .created(accountVO.getCreated())
                .updated(accountVO.getUpdated())
                .id(accountVO.getId())
                .age(accountVO.getAge())
                .email(accountVO.getEmail())
                .name(accountVO.getName())
                .addresses(accountVO.getAddresses() != null ? accountVO.getAddresses() .parallelStream().map(addressVO -> addressService.getAddress(addressVO))
                		.collect(Collectors.toList()) : Collections.emptyList())
                .build() : Account.builder().build();
    }
}
