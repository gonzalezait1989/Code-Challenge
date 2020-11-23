package com.wefox.challenge.service;

import org.springframework.stereotype.Service;

import com.wefox.challenge.model.Address;
import com.wefox.challenge.vo.AddressVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

  /**
   * Transform Address to AddressVO
   * 
   * @param address
   * @return
   */
  AddressVO getAddressVO(Address address) {
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
  Address getAddress(AddressVO addressVO) {
    return addressVO != null
        ? Address.builder().created(addressVO.getCreated()).updated(addressVO.getUpdated())
            .id(addressVO.getId()).address(addressVO.getAddress()).build()
        : Address.builder().build();
  }
}
