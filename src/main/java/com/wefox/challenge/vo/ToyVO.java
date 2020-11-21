package com.wefox.challenge.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToyVO {
	private Long id;
	private String name;
    private Date created;
    private Date updated;
}
