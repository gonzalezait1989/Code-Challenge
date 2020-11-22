package com.wefox.challenge.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ProductVO {
    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
    private LocalDateTime created;
    private LocalDateTime updated;
}
