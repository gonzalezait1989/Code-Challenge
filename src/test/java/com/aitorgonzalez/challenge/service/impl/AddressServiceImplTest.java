package com.aitorgonzalez.challenge.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import com.aitorgonzalez.challenge.model.Address;
import com.aitorgonzalez.challenge.vo.AddressVO;

@RunWith(PowerMockRunner.class)
public class AddressServiceImplTest {

	@InjectMocks
	private AddressServiceImpl addressService;

	@Test
	public void testGetAddress() throws Exception {
		AddressVO addressVO = AddressVO.builder().build();
		addressVO.setId(1L);
		addressVO.setAddress("My street 1234");
		addressVO.setCreated(Calendar.getInstance().getTime());
		addressVO.setUpdated(Calendar.getInstance().getTime());

		Address address = addressService.getAddress(addressVO);

		assertThat(address).isNotNull();
		assertThat(address.getId()).isEqualTo(addressVO.getId());
		assertThat(address.getAddressDetails()).isEqualTo(addressVO.getAddress());
		assertThat(address.getCreated()).isEqualTo(addressVO.getCreated());
		assertThat(address.getUpdated()).isEqualTo(addressVO.getUpdated());
	}

	@Test
	public void testGetAddressVO() throws Exception {
		Address address = new Address();
		address.setId(1L);
		address.setAddressDetails("My street 1234");
		address.setCreated(Calendar.getInstance().getTime());
		address.setUpdated(Calendar.getInstance().getTime());

		AddressVO addressVO = addressService.getAddressVO(address);

		assertThat(addressVO).isNotNull();
		assertThat(addressVO.getId()).isEqualTo(address.getId());
		assertThat(addressVO.getAddress()).isEqualTo(address.getAddressDetails());
		assertThat(addressVO.getCreated()).isEqualTo(address.getCreated());
		assertThat(addressVO.getUpdated()).isEqualTo(address.getUpdated());
	}

}
