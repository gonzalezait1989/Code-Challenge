package com.wefox.challenge.service;

import com.wefox.challenge.model.Address;
import com.wefox.challenge.vo.AddressVO;

/**
 * Service to manage Addresses.
 * 
 * @author aitor
 */
public interface AddressService {

  /**
   * Transform Address to AddressVO
   * 
   * @param address the Address to transform.
   * @return the AddressVO.
   */
  public AddressVO getAddressVO(Address address);

  /**
   * Transform AddressVO to Address
   * 
   * @param address the AddressVO to transform.
   * @return the Address.
   */
  public Address getAddress(AddressVO addressVO);

}
