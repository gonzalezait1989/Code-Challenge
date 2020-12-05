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
@XmlRootElement(name = "Account")
public class AccountVO {
	
	@XmlElementWrapper(name="addresses")
    @XmlElement(name="address")
	private List<AddressVO> addresses;
    
    @XmlElement(type = Integer.class)
	private Integer age;
    
    @XmlElement(type = Date.class)
    @XmlSchemaType(name="date")
	private Date created;
    
    @XmlElement
	private String email;
    
    @XmlElement(type = Long.class)
	private Long id;
    
    @XmlElement
	private String name;
    
    @XmlElement(type = Date.class)
    @XmlSchemaType(name="date")
	private Date updated;
}
