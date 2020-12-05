package com.aitorgonzalez.challenge.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@XmlRootElement(name = "Address")
public class AddressVO {
    
	@XmlElement
	private String address;
	
	@XmlElement(type = Date.class)
	@XmlSchemaType(name="date")
	private Date created;
	
	@XmlElement(type = Long.class)
	private Long id;
	
	@XmlElement(type = Date.class)
	@XmlSchemaType(name="date")
	private Date updated;
}
