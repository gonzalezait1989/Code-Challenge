package com.wefox.challenge.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ThreadInfo {

	private Long threadNo;
	private String stepName;
	private Date start;
	private Date finish;
}
