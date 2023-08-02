package com.esperanca.hopefood.domain.models;

import com.esperanca.hopefood.domain.models.embeddables.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
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

  @OneToMany(mappedBy = "restaurant")
  private List<Product> products;

  @JoinColumn(nullable = false, columnDefinition = "datetime")
  @CreationTimestamp
  private OffsetDateTime createdAt;

  @JoinColumn(nullable = false, columnDefinition = "datetime")
  @UpdateTimestamp
  private OffsetDateTime updatedAt;
}