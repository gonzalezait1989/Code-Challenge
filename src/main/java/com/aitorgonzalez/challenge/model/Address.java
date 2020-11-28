package com.aitorgonzalez.challenge.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "ADDRESS")
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Address {

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "address__c")
	private String addressDetails;

	@CreationTimestamp
	@Column(name = "created__c", updatable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date created;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@UpdateTimestamp
	@Column(name = "updated__c")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updated;
}
