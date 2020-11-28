package com.aitorgonzalez.challenge.service;

import com.aitorgonzalez.challenge.model.Address;
import com.aitorgonzalez.challenge.vo.AddressVO;

/**
 * Service to manage Addresses.
 * 
 * @author aitor
 */
public interface AddressService {

	/**
	 * Transform AddressVO to Address
	 * 
	 * @param address the AddressVO to transform.
	 * @return the Address.
	 */
	static Address getAddress(AddressVO addressVO) {
		return addressVO != null
				? Address.builder().created(addressVO.getCreated()).updated(addressVO.getUpdated())
						.id(addressVO.getId()).address(addressVO.getAddress()).build()
				: Address.builder().build();
	}

	/**
	 * Transform Address to AddressVO
	 * 
	 * @param address the Address to transform.
	 * @return the AddressVO.
	 */
	static AddressVO getAddressVO(Address address) {
		return address != null
				? AddressVO.builder().created(address.getCreated()).updated(address.getUpdated()).id(address.getId())
						.address(address.getAddress()).build()
				: AddressVO.builder().build();
	}

}
