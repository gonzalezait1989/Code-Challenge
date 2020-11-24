package com.wefox.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.wefox.challenge.model.Address;
import com.wefox.challenge.service.AddressService;
import com.wefox.challenge.vo.AddressVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  /**
   * Transform Address to AddressVO
   * 
   * @param address
   * @return
   */
  @Override
  public AddressVO getAddressVO(Address address) {
    return address != null
        ? AddressVO.builder().created(address.getCreated()).updated(address.getUpdated())
            .id(address.getId()).address(address.getAddress()).build()
        : AddressVO.builder().build();
  }

  /**
   * Transform Address to AddressVO
   * 
   * @param addressVO
   * @return
   */
  @Override
  public Address getAddress(AddressVO addressVO) {
    return addressVO != null
        ? Address.builder().created(addressVO.getCreated()).updated(addressVO.getUpdated())
            .id(addressVO.getId()).address(addressVO.getAddress()).build()
        : Address.builder().build();
  }
}
