package com.aitorgonzalez.challenge.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "ACCOUNT")
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Account {

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Address> addresses;

	@Column(name = "age__c")
	private Integer age;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName="id", nullable = false)
	private Company company;

	@CreationTimestamp
	@Column(name = "created__c", updatable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "email__c")
	private String email;

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
