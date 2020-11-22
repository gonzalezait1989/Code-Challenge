package com.wefox.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.wefox.challenge.model.Account;
import com.wefox.challenge.model.Address;
import com.wefox.challenge.repository.AccountRepository;
import com.wefox.challenge.vo.AccountVO;
import com.wefox.challenge.vo.AddressVO;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private AddressService addressService;
	
	@Mock
	private AccountRepository accountRespository;
	

	@Test
	public void testFindAll() throws Exception {
		Account account1 = new Account();
		Account account2 = new Account();
		List<Account> accounts = new ArrayList<Account>();
		accounts.addAll(Arrays.asList(account1, account2));

		when(accountRespository.findAll()).thenReturn(accounts);

		List<AccountVO> accountVOs = this.accountService.findAll();
		assertThat(accountVOs).isNotNull();
		assertThat(accountVOs).isNotEmpty();
		assertThat(accountVOs).hasSize(2);
	}

	@Test
	public void testSave() throws Exception {
		AccountVO account1 = AccountVO.builder().build();
		AccountVO accountResult = this.accountService.save(account1);
		verify(accountRespository, times(1)).save(any(Account.class));
		assertThat(accountResult).isNotNull();
	}

	@Test
	public void testFindById() throws Exception {
		Account account = new Account();

		account.setId(1l);
		account.setAge(25);
		account.setEmail("aitor.gonzalez.martinez@gmail.com");
		account.setName("Aitor González Martínez");
		account.setCreated(OffsetDateTime.now().toLocalDateTime());
		account.setUpdated(OffsetDateTime.now().toLocalDateTime());
		
		Address address = new Address();
		address.setId(1l);
		address.setAccount(account);
		address.setAddress("127.0.0.1");
		address.setCreated(OffsetDateTime.now().toLocalDateTime());
		address.setUpdated(OffsetDateTime.now().toLocalDateTime());
		account.setAddresses(Arrays.asList(address));
		
		AddressVO addressVO = AddressVO.builder()
				.id(address.getId())
				.address(address.getAddress())
				.created(address.getCreated())
				.updated(address.getUpdated()).build();

		when(accountRespository.findById(1l)).thenReturn(Optional.of(account));
		when(accountRespository.findById(2l)).thenReturn(Optional.empty());
		when(addressService.getAddressVO(address)).thenReturn(addressVO);
		Optional<AccountVO> accountVO1 = this.accountService.findById(1l);
		Optional<AccountVO> accountVO2 = this.accountService.findById(2l);

		assertThat(accountVO1).isNotNull();
		assertThat(accountVO1).isPresent();
		assertThat(accountVO1.get().getId()).isEqualTo(account.getId());
		assertThat(accountVO1.get().getAge()).isEqualTo(account.getAge());
		assertThat(accountVO1.get().getEmail()).isEqualTo(account.getEmail());
		assertThat(accountVO1.get().getName()).isEqualTo(account.getName());
		assertThat(accountVO1.get().getCreated()).isEqualTo(account.getCreated());
		assertThat(accountVO1.get().getUpdated()).isEqualTo(account.getUpdated());
		assertThat(accountVO1.get().getAddresses()).isNotNull();
		assertThat(accountVO1.get().getAddresses()).isNotEmpty();
		assertThat(accountVO1.get().getAddresses()).hasSize(1);
		assertThat(accountVO1.get().getAddresses().get(0).getAddress()).isEqualTo(address.getAddress());
		assertThat(accountVO1.get().getAddresses().get(0).getId()).isEqualTo(address.getId());
		assertThat(accountVO1.get().getAddresses().get(0).getCreated()).isEqualTo(address.getCreated());
		assertThat(accountVO1.get().getAddresses().get(0).getUpdated()).isEqualTo(address.getUpdated());

		assertThat(accountVO2).isNotNull();
		assertThat(accountVO2).isNotPresent();
	}

	@Test
	public void testFindByEmail() throws Exception {
		Account account = new Account();

		account.setId(1l);
		account.setAge(25);
		account.setEmail("aitor.gonzalez.martinez@gmail.com");
		account.setName("Aitor González Martínez");
		account.setCreated(OffsetDateTime.now().toLocalDateTime());
		account.setUpdated(OffsetDateTime.now().toLocalDateTime());
		Address address = new Address();
		address.setId(1l);
		address.setAccount(account);
		address.setAddress("127.0.0.1");
		address.setCreated(OffsetDateTime.now().toLocalDateTime());
		address.setUpdated(OffsetDateTime.now().toLocalDateTime());

		account.setAddresses(Arrays.asList(address));
		
		AddressVO addressVO = AddressVO.builder()
				.id(address.getId())
				.address(address.getAddress())
				.created(address.getCreated())
				.updated(address.getUpdated()).build();

		when(accountRespository.findByEmail("aitor.gonzalez.martinez@gmail.com"))
				.thenReturn(Optional.of(account));
		when(accountRespository.findByEmail("aitor@gmail.com")).thenReturn(Optional.empty());

		when(addressService.getAddressVO(address)).thenReturn(addressVO);
		Optional<AccountVO> accountVO1 = this.accountService.findByEmail("aitor.gonzalez.martinez@gmail.com");
		Optional<AccountVO> accountVO2 = this.accountService.findByEmail("aitor@gmail.com");

		assertThat(accountVO1).isNotNull();
		assertThat(accountVO1).isPresent();
		assertThat(accountVO1.get().getId()).isEqualTo(account.getId());
		assertThat(accountVO1.get().getAge()).isEqualTo(account.getAge());
		assertThat(accountVO1.get().getEmail()).isEqualTo(account.getEmail());
		assertThat(accountVO1.get().getName()).isEqualTo(account.getName());
		assertThat(accountVO1.get().getCreated()).isEqualTo(account.getCreated());
		assertThat(accountVO1.get().getUpdated()).isEqualTo(account.getUpdated());
		assertThat(accountVO1.get().getAddresses()).isNotNull();
		assertThat(accountVO1.get().getAddresses()).isNotEmpty();
		assertThat(accountVO1.get().getAddresses()).hasSize(1);
		assertThat(accountVO1.get().getAddresses().get(0).getAddress()).isEqualTo(address.getAddress());
		assertThat(accountVO1.get().getAddresses().get(0).getId()).isEqualTo(address.getId());
		assertThat(accountVO1.get().getAddresses().get(0).getCreated()).isEqualTo(address.getCreated());
		assertThat(accountVO1.get().getAddresses().get(0).getUpdated()).isEqualTo(address.getUpdated());

		assertThat(accountVO2).isNotNull();
		assertThat(accountVO2).isNotPresent();
	}

	@Test
	public void testExistsById() throws Exception {
		when(accountRespository.existsById(1l)).thenReturn(true);
		when(accountRespository.existsById(2l)).thenReturn(false);

		assertThat(this.accountService.existsById(1l)).isTrue();
		assertThat(this.accountService.existsById(2l)).isFalse();
	}

	@Test
	public void testDeleteById() throws Exception {
		this.accountService.deleteById(1l);
		verify(accountRespository, times(1)).deleteById(1l);
	}

}
