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
import org.powermock.modules.junit4.PowerMockRunner;

import com.aitorgonzalez.challenge.model.Account;
import com.aitorgonzalez.challenge.model.Address;
import com.aitorgonzalez.challenge.model.Company;
import com.aitorgonzalez.challenge.repository.CompanyRepository;
import com.aitorgonzalez.challenge.vo.AccountVO;
import com.aitorgonzalez.challenge.vo.AddressVO;
import com.aitorgonzalez.challenge.vo.CompanyVO;

@RunWith(PowerMockRunner.class)
public class CompanyServiceImplTest {

	

	@Mock
	private CompanyRepository companyRespository;
	
	@InjectMocks
	private CompanyServiceImpl companyService;

	@Mock
	private AccountServiceImpl accountService;

	@Mock
	private AddressServiceImpl addressService;

	Company company = null;
	Account account = null;
	Address address = null;
	
	@Before
	public void prepareDataSet() {
		
		company = new Company();
		company.setId(1L);
		company.setName("Aitor Industries");
		company.setCreated(Calendar.getInstance().getTime());
		company.setUpdated(Calendar.getInstance().getTime());
		
		
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
		
		company.setAccounts(Arrays.asList(account));

		
		
		AddressVO addressVO = AddressVO.builder().id(address.getId()).address(address.getAddressDetails())
				.created(address.getCreated()).updated(address.getUpdated()).build();
		
		AccountVO accountVO = AccountVO.builder().id(account.getId()).addresses(Arrays.asList(addressVO)).age(account.getAge())
				.created(account.getCreated()).email(account.getEmail()).name(account.getName()).updated(account.getUpdated()).build();

		when(companyRespository.findById(1L)).thenReturn(Optional.of(company));
		when(companyRespository.findById(2L)).thenReturn(Optional.empty());
		when(companyRespository.findByName("Aitor Industries")).thenReturn(Optional.of(company));
		when(companyRespository.findByName("Another Industries")).thenReturn(Optional.empty());
		when(addressService.getAddressVO(address)).thenReturn(addressVO);
		when(accountService.getAccountVO(account)).thenReturn(accountVO);
	}

	@Test
	public void testDeleteById() throws Exception {
		this.companyService.deleteById(1L);
		verify(companyRespository, times(1)).deleteById(1L);
	}

	@Test
	public void testExistsById() throws Exception {
		when(companyRespository.existsById(1L)).thenReturn(true);
		when(companyRespository.existsById(2L)).thenReturn(false);

		assertThat(this.companyService.existsById(1L)).isTrue();
		assertThat(this.companyService.existsById(2L)).isFalse();
	}

	@Test
	public void testFindAll() throws Exception {
		Company company1 = new Company();
		Company company2 = new Company();
		List<Company> companies = new ArrayList<Company>();
		companies.addAll(Arrays.asList(company1, company2));

		when(companyRespository.findAll()).thenReturn(companies);

		List<CompanyVO> companyVOs = this.companyService.findAll();
		assertThat(companyVOs).isNotNull().isNotEmpty().hasSize(2);
	}

	@Test
	public void testFindByName() throws Exception {
		Optional<CompanyVO> companyVO1 = this.companyService.findByName("Aitor Industries");
		Optional<CompanyVO> companyVO2 = this.companyService.findByName("Another Industries");

		assertThat(companyVO1).isNotNull().isPresent();
		assertThat(companyVO1.get().getId()).isEqualTo(company.getId());
		assertThat(companyVO1.get().getName()).isEqualTo(company.getName());
		assertThat(companyVO1.get().getName()).isEqualTo(company.getName());
		assertThat(companyVO1.get().getCreated()).isEqualTo(company.getCreated());
		assertThat(companyVO1.get().getUpdated()).isEqualTo(company.getUpdated());
		assertThat(companyVO1.get().getAccounts()).isNotNull().isNotEmpty().hasSize(1);
		assertThat(companyVO1.get().getAccounts().get(0).getId()).isEqualTo(account.getId());
		assertThat(companyVO1.get().getAccounts().get(0).getAge()).isEqualTo(account.getAge());
		assertThat(companyVO1.get().getAccounts().get(0).getEmail()).isEqualTo(account.getEmail());
		assertThat(companyVO1.get().getAccounts().get(0).getName()).isEqualTo(account.getName());
		assertThat(companyVO1.get().getAccounts().get(0).getCreated()).isEqualTo(account.getCreated());
		assertThat(companyVO1.get().getAccounts().get(0).getUpdated()).isEqualTo(account.getUpdated());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses()).isNotNull().isNotEmpty().hasSize(1);
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getAddress()).isEqualTo(address.getAddressDetails());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getId()).isEqualTo(address.getId());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getCreated()).isEqualTo(address.getCreated());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getUpdated()).isEqualTo(address.getUpdated());

		assertThat(companyVO2).isNotNull().isNotPresent();
	}

	@Test
	public void testFindById() throws Exception {

		Optional<CompanyVO> companyVO1 = this.companyService.findById(1L);
		Optional<CompanyVO> companyVO2 = this.companyService.findById(2L);

		assertThat(companyVO1).isNotNull().isPresent();
		assertThat(companyVO1.get().getId()).isEqualTo(company.getId());
		assertThat(companyVO1.get().getName()).isEqualTo(company.getName());
		assertThat(companyVO1.get().getName()).isEqualTo(company.getName());
		assertThat(companyVO1.get().getCreated()).isEqualTo(company.getCreated());
		assertThat(companyVO1.get().getUpdated()).isEqualTo(company.getUpdated());
		assertThat(companyVO1.get().getAccounts()).isNotNull().isNotEmpty().hasSize(1);
		assertThat(companyVO1.get().getAccounts().get(0).getId()).isEqualTo(account.getId());
		assertThat(companyVO1.get().getAccounts().get(0).getAge()).isEqualTo(account.getAge());
		assertThat(companyVO1.get().getAccounts().get(0).getEmail()).isEqualTo(account.getEmail());
		assertThat(companyVO1.get().getAccounts().get(0).getName()).isEqualTo(account.getName());
		assertThat(companyVO1.get().getAccounts().get(0).getCreated()).isEqualTo(account.getCreated());
		assertThat(companyVO1.get().getAccounts().get(0).getUpdated()).isEqualTo(account.getUpdated());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses()).isNotNull().isNotEmpty().hasSize(1);
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getAddress()).isEqualTo(address.getAddressDetails());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getId()).isEqualTo(address.getId());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getCreated()).isEqualTo(address.getCreated());
		assertThat(companyVO1.get().getAccounts().get(0).getAddresses().get(0).getUpdated()).isEqualTo(address.getUpdated());

		assertThat(companyVO2).isNotNull().isNotPresent();
	}

	@Test
	public void testSave() throws Exception {
		CompanyVO company1 = CompanyVO.builder().build();
		CompanyVO companyResult = this.companyService.save(company1);
		verify(companyRespository, times(1)).save(any(Company.class));
		assertThat(companyResult).isNotNull();
	}

}
