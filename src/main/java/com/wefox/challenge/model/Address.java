package com.wefox.challenge.model;

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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "address__c")
  private String address;

  @JsonIgnore
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @CreationTimestamp
  @Column(name = "created__c", updatable = false)
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date created;

  @UpdateTimestamp
  @Column(name = "updated__c")
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date updated;
}
