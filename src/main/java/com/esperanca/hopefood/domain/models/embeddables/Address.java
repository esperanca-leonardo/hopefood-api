package com.esperanca.hopefood.domain.models.embeddables;

import com.esperanca.hopefood.domain.models.City;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Address {
	private String cep;
	private String publicPlace;
	private String number;
	private String complement;
	private String district;

	@ManyToOne
	@JoinColumn(nullable = false)
	private City city;
}
