package com.wefox.challenge.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class AddressVO {
  private Long id;
  private String address;
  private Date created;
  private Date updated;
}
