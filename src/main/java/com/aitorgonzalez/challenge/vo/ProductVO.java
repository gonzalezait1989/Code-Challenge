package com.aitorgonzalez.challenge.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ProductVO {
	private Date created;
	private String description;
	private Long id;
	private String name;
	private BigDecimal price;
	private Integer stock;
	private Date updated;
}
