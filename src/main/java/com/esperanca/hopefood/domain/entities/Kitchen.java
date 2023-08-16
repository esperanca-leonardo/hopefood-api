package com.esperanca.hopefood.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Kitchen {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  private String name;
  private String description;

  @OneToMany(mappedBy = "kitchen")
  private List<Restaurant> restaurants;

  @CreationTimestamp
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  private OffsetDateTime updatedAt;
}