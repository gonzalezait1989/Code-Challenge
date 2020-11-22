package com.wefox.challenge.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressVO {
	private Long id;
	private String address;
    private LocalDateTime created;
    private LocalDateTime updated;
}
