package com.aitorgonzalez.challenge.vo;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@XmlRootElement(name = "Company")
public class CompanyVO {
	
	@XmlElement(type = Long.class)
	private Long id;
	
	@XmlElement
	private String name;
	
	@XmlElementWrapper(name="accounts")
    @XmlElement(name="account")
	private List<AccountVO> accounts;
	
	@XmlElement(type = Date.class)
	@XmlSchemaType(name="date")
	private Date created;
	
	@XmlElement(type = Date.class)
	@XmlSchemaType(name="date")
	private Date updated;
}
