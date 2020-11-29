package com.aitorgonzalez.challenge.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.model.Address;
import com.aitorgonzalez.challenge.repository.AccountRepository;
import com.aitorgonzalez.challenge.vo.AccountVO;
import com.aitorgonzalez.challenge.vo.AddressVO;

@RunWith(PowerMockRunner.class)
public class AccountServiceImplTest {

	Account account = null;

	@Mock
	private AccountRepository accountRespository;

	@InjectMocks
	private AccountServiceImpl accountService;

	Address address = null;
	@Mock
	private AddressServiceImpl addressService;

	@Before
	public void prepareDataSet() {
		account = new Account();
		account.setId(1L);
		account.setAge(25);
		account.setEmail("aitor.gonzalez.martinez@gmail.com");
		account.setName("Aitor González Martínez");
		account.setCreated(Calendar.getInstance().getTime());
		account.setUpdated(Calendar.getInstance().getTime());

		address = new Address();
		address.setId(1L);
		address.setAccount(account);
		address.setAddressDetails("127.0.0.1");
		address.setCreated(Calendar.getInstance().getTime());
		address.setUpdated(Calendar.getInstance().getTime());
		account.setAddresses(Arrays.asList(address));

		AddressVO addressVO = AddressVO.builder().id(address.getId()).address(address.getAddressDetails())
				.created(address.getCreated()).updated(address.getUpdated()).build();

		when(accountRespository.findById(1L)).thenReturn(Optional.of(account));
		when(accountRespository.findById(2L)).thenReturn(Optional.empty());
		when(accountRespository.findByEmail("aitor.gonzalez.martinez@gmail.com")).thenReturn(Optional.of(account));
		when(accountRespository.findByEmail("aitor@gmail.com")).thenReturn(Optional.empty());

		when(addressService.getAddressVO(address)).thenReturn(addressVO);
	}

	@Test
	public void testDeleteById() throws Exception {
		this.accountService.deleteById(1L);
		verify(accountRespository, times(1)).deleteById(1L);
	}

	@Test
	public void testExistsById() throws Exception {
		when(accountRespository.existsById(1L)).thenReturn(true);
		when(accountRespository.existsById(2L)).thenReturn(false);

		assertThat(this.accountService.existsById(1L)).isTrue();
		assertThat(this.accountService.existsById(2L)).isFalse();
	}

	@Test
	public void testFindAll() throws Exception {
		Account account1 = new Account();
		Account account2 = new Account();
		List<Account> accounts = new ArrayList<Account>();
		accounts.addAll(Arrays.asList(account1, account2));

		when(accountRespository.findAll()).thenReturn(accounts);

		List<AccountVO> accountVOs = this.accountService.findAll();
		assertThat(accountVOs).isNotNull().isNotEmpty().hasSize(2);
	}

	@Test
	public void testFindByEmail() throws Exception {
		Optional<AccountVO> accountVO1 = this.accountService.findByEmail("aitor.gonzalez.martinez@gmail.com");
		Optional<AccountVO> accountVO2 = this.accountService.findByEmail("aitor@gmail.com");

		assertThat(accountVO1).isNotNull().isPresent();
		assertThat(accountVO1.get().getId()).isEqualTo(account.getId());
		assertThat(accountVO1.get().getAge()).isEqualTo(account.getAge());
		assertThat(accountVO1.get().getEmail()).isEqualTo(account.getEmail());
		assertThat(accountVO1.get().getName()).isEqualTo(account.getName());
		assertThat(accountVO1.get().getCreated()).isEqualTo(account.getCreated());
		assertThat(accountVO1.get().getUpdated()).isEqualTo(account.getUpdated());
		assertThat(accountVO1.get().getAddresses()).isNotNull();
		assertThat(accountVO1.get().getAddresses()).isNotEmpty();
		assertThat(accountVO1.get().getAddresses()).hasSize(1);
		assertThat(accountVO1.get().getAddresses().get(0).getAddress()).isEqualTo(address.getAddressDetails());
		assertThat(accountVO1.get().getAddresses().get(0).getId()).isEqualTo(address.getId());
		assertThat(accountVO1.get().getAddresses().get(0).getCreated()).isEqualTo(address.getCreated());
		assertThat(accountVO1.get().getAddresses().get(0).getUpdated()).isEqualTo(address.getUpdated());

		assertThat(accountVO2).isNotNull().isNotPresent();
	}

	@Test
	public void testFindById() throws Exception {

		Optional<AccountVO> accountVO1 = this.accountService.findById(1L);
		Optional<AccountVO> accountVO2 = this.accountService.findById(2L);

		assertThat(accountVO1).isNotNull().isPresent();
		assertThat(accountVO1.get().getId()).isEqualTo(account.getId());
		assertThat(accountVO1.get().getAge()).isEqualTo(account.getAge());
		assertThat(accountVO1.get().getEmail()).isEqualTo(account.getEmail());
		assertThat(accountVO1.get().getName()).isEqualTo(account.getName());
		assertThat(accountVO1.get().getCreated()).isEqualTo(account.getCreated());
		assertThat(accountVO1.get().getUpdated()).isEqualTo(account.getUpdated());
		assertThat(accountVO1.get().getAddresses()).isNotNull();
		assertThat(accountVO1.get().getAddresses()).isNotEmpty();
		assertThat(accountVO1.get().getAddresses()).hasSize(1);
		assertThat(accountVO1.get().getAddresses().get(0).getAddress()).isEqualTo(address.getAddressDetails());
		assertThat(accountVO1.get().getAddresses().get(0).getId()).isEqualTo(address.getId());
		assertThat(accountVO1.get().getAddresses().get(0).getCreated()).isEqualTo(address.getCreated());
		assertThat(accountVO1.get().getAddresses().get(0).getUpdated()).isEqualTo(address.getUpdated());

		assertThat(accountVO2).isNotNull().isNotPresent();
	}

	@Test
	public void testSave() throws Exception {
		AccountVO account1 = AccountVO.builder().build();
		AccountVO accountResult = this.accountService.save(account1);
		verify(accountRespository, times(1)).save(any(Account.class));
		assertThat(accountResult).isNotNull();
	}

}
