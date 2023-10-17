package com.esperanca.hopefood.domain.formofpayment.entities;

import com.esperanca.hopefood.core.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class FormOfPayment extends AbstractEntity {

	private String description;
}
