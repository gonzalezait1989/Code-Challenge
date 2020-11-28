package com.aitorgonzalez.challenge.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class AddressVO {
	private String address;
	private Date created;
	private Long id;
	private Date updated;
}
