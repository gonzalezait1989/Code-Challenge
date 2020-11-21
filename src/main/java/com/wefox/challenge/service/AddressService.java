package com.wefox.challenge.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wefox.challenge.model.Account;
import com.wefox.challenge.model.Address;
import com.wefox.challenge.repository.AddressRepository;
import com.wefox.challenge.vo.AccountVO;
import com.wefox.challenge.vo.AddressVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
	
	@Autowired
    private  AddressRepository addressRespository;
	
	/**
     * Transform Address to AddressVO
     * @param address
     * @return
     */
    AddressVO getAddressVO(Address address) {
        return AddressVO.builder()
                .created(address.getCreated())
                .updated(address.getUpdated())
                .id(address.getId())
                .address(address.getAddress())
                .build();
    }
    
    /**
     * Transform Address to AddressVO
     * @param addressVO
     * @return
     */
    Address getAddress(AddressVO addressVO) {
    	return Address.builder()
                .created(addressVO.getCreated())
                .updated(addressVO.getUpdated())
                .id(addressVO.getId())
                .address(addressVO.getAddress())
                .build();
    }
}
