package com.wefox.challenge.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressVO {
	private Long id;
	private String address;
    private Date created;
    private Date updated;
}
