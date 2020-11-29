package com.aitorgonzalez.challenge.vo;

import java.util.Date;
import java.util.List;

import com.aitorgonzalez.challenge.model.Account;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@JacksonXmlRootElement(localName = "Company")
public class CompanyVO {
	
	@JacksonXmlProperty(isAttribute = true)
	private Long id;
	
	@JacksonXmlProperty
	private String name;
	
	@JacksonXmlProperty
	private List<AccountVO> accounts;
	
	@JacksonXmlProperty
	private Date created;
	
	@JacksonXmlProperty
	private Date updated;
}
