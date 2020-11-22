package com.wefox.challenge.vo;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

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
