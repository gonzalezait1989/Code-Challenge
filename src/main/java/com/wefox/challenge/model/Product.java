package com.wefox.challenge.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name="PRODUCT")
@Data
@Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name__c")
    private String name;

    @Column(name = "description__c")
    private String description;

    @Column(name = "price__c")
    private BigDecimal price;

    @Column(name = "stock__c")
    private Integer stock;

    @CreationTimestamp
    @Column(name = "created__c", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated__c")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private LocalDateTime updated;
}
