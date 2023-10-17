package com.esperanca.hopefood.domain.restaurant.entities;

import com.esperanca.hopefood.domain.address.embeddables.Address;
import com.esperanca.hopefood.core.entities.AbstractEntity;
import com.esperanca.hopefood.domain.formofpayment.entities.FormOfPayment;
import com.esperanca.hopefood.domain.kitchen.entities.Kitchen;
import com.esperanca.hopefood.domain.product.entities.Product;
import com.esperanca.hopefood.domain.state.entities.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Restaurant extends AbstractEntity {

  private String name;
  private BigDecimal freightValue;

  @ManyToOne
  private Kitchen kitchen;

  @ManyToMany
  private List<FormOfPayment> formOfPayments;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "restaurant")
  private List<Product> products;
}