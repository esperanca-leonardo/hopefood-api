package com.esperanca.hopefood.domain.models;

import com.esperanca.hopefood.domain.models.embeddables.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private BigDecimal freightValue;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Kitchen kitchen;

  @ManyToMany
  private List<FormOfPayment> formOfPayments;

  @Embedded
  private Address address;
}