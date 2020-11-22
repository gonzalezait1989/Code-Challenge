package com.wefox.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.OffsetDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.wefox.challenge.model.Address;
import com.wefox.challenge.vo.AddressVO;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

	@InjectMocks
	private AddressService addressService;
	
	@Test
	public void testGetAddressVO() throws Exception {
		Address address  = new Address();
		address.setId(1l);
		address.setAddress("My street 1234");
		address.setCreated(OffsetDateTime.now().toLocalDateTime());
		address.setUpdated(OffsetDateTime.now().toLocalDateTime());
		
		AddressVO addressVO = this.addressService.getAddressVO(address);
		
		assertThat(addressVO).isNotNull();
		assertThat(addressVO.getId()).isEqualTo(address.getId());
		assertThat(addressVO.getAddress()).isEqualTo(address.getAddress());
		assertThat(addressVO.getCreated()).isEqualTo(address.getCreated());
		assertThat(addressVO.getUpdated()).isEqualTo(address.getUpdated());
	}

	@Test
	public void testGetAddress() throws Exception {
		AddressVO addressVO  = AddressVO.builder().build();
		addressVO.setId(1l);
		addressVO.setAddress("My street 1234");
		addressVO.setCreated(OffsetDateTime.now().toLocalDateTime());
		addressVO.setUpdated(OffsetDateTime.now().toLocalDateTime());
		
		Address address = this.addressService.getAddress(addressVO);
		
		assertThat(address).isNotNull();
		assertThat(address.getId()).isEqualTo(addressVO.getId());
		assertThat(address.getAddress()).isEqualTo(addressVO.getAddress());
		assertThat(address.getCreated()).isEqualTo(addressVO.getCreated());
		assertThat(address.getUpdated()).isEqualTo(addressVO.getUpdated());
	}

}
