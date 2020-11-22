package com.wefox.challenge.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadInfoVO {
	private Long threadNo;
	private String stepName;
	private LocalDateTime start;
	private LocalDateTime finish;
}
