package com.wefox.challenge.vo;

import java.time.LocalDateTime;
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
    private LocalDateTime created;
    private LocalDateTime updated;
}
