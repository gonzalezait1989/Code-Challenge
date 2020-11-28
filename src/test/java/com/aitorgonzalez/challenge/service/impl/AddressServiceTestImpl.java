package com.aitorgonzalez.challenge.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.aitorgonzalez.challenge.model.Address;
import com.aitorgonzalez.challenge.service.AddressService;
import com.aitorgonzalez.challenge.vo.AddressVO;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTestImpl {

  @InjectMocks
  private AddressServiceImpl addressService;

  @Test
  public void testGetAddressVO() throws Exception {
    Address address = new Address();
    address.setId(1L);
    address.setAddress("My street 1234");
    address.setCreated(Calendar.getInstance().getTime());
    address.setUpdated(Calendar.getInstance().getTime());

    AddressVO addressVO = AddressService.getAddressVO(address);

    assertThat(addressVO).isNotNull();
    assertThat(addressVO.getId()).isEqualTo(address.getId());
    assertThat(addressVO.getAddress()).isEqualTo(address.getAddress());
    assertThat(addressVO.getCreated()).isEqualTo(address.getCreated());
    assertThat(addressVO.getUpdated()).isEqualTo(address.getUpdated());
  }

  @Test
  public void testGetAddress() throws Exception {
    AddressVO addressVO = AddressVO.builder().build();
    addressVO.setId(1L);
    addressVO.setAddress("My street 1234");
    addressVO.setCreated(Calendar.getInstance().getTime());
    addressVO.setUpdated(Calendar.getInstance().getTime());

    Address address = AddressService.getAddress(addressVO);

    assertThat(address).isNotNull();
    assertThat(address.getId()).isEqualTo(addressVO.getId());
    assertThat(address.getAddress()).isEqualTo(addressVO.getAddress());
    assertThat(address.getCreated()).isEqualTo(addressVO.getCreated());
    assertThat(address.getUpdated()).isEqualTo(addressVO.getUpdated());
  }

}
