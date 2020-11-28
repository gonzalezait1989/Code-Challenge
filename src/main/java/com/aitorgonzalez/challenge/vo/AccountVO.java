package com.aitorgonzalez.challenge.vo;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class AccountVO {
	private List<AddressVO> addresses;
	private Integer age;
	private Date created;
	private String email;
	private Long id;
	private String name;
	private Date updated;
}
