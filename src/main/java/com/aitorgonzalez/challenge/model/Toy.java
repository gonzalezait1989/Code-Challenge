package com.aitorgonzalez.challenge.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "TOY")
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Toy {

	@CreationTimestamp
	@Column(name = "created__c", updatable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date created;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name__c")
	private String name;

	@UpdateTimestamp
	@Column(name = "updated__c")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated;
}
