package com.esperanca.hopefood.domain.kitchen.entities;

import com.esperanca.hopefood.domain.restaurant.entities.Restaurant;
import com.esperanca.hopefood.core.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Kitchen extends AbstractEntity {

  private String name;
  private String description;

  @OneToMany(mappedBy = "kitchen")
  private List<Restaurant> restaurants;
}