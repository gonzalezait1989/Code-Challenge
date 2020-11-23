package com.wefox.challenge.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ProductVO {
  private Long id;
  private String name;
  private String description;
  private Integer stock;
  private BigDecimal price;
  private Date created;
  private Date updated;
}
