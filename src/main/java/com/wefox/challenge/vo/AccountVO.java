package com.wefox.challenge.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.wefox.challenge.model.Address;

@Data
@Builder
public class AccountVO {
	private Long id;
	private String name;
	private String email;
	private Integer age;
	private List<AddressVO> addresses;
    private Date created;
    private Date updated;
}
