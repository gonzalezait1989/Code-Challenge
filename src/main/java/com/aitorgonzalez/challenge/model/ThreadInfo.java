package com.aitorgonzalez.challenge.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ThreadInfo {

	private Date finish;
	private Date start;
	private String stepName;
	private Long threadNo;
}
