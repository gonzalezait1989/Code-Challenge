package com.wefox.challenge.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ThreadInfoVO {
  private Long threadNo;
  private String stepName;
  private Date start;
  private Date finish;
}
