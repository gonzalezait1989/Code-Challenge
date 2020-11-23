package com.wefox.challenge.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "ACCOUNT")
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name__c")
  private String name;

  @Column(name = "email__c")
  private String email;

  @Column(name = "age__c")
  private Integer age;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
  private List<Address> addresses;

  @CreationTimestamp
  @Column(name = "created__c", updatable = false)
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date created;

  @UpdateTimestamp
  @Column(name = "updated__c")
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date updated;

}
