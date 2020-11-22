package com.wefox.challenge.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToyVO {
	private Long id;
	private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
}
