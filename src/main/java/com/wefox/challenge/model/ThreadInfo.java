package com.wefox.challenge.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ThreadInfo {

	private Long threadNo;
	private String stepName;
	private LocalDateTime start;
	private LocalDateTime finish;
}
