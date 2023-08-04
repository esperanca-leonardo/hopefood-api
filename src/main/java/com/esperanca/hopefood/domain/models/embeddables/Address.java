package com.esperanca.hopefood.domain.models.embeddables;

import com.esperanca.hopefood.domain.models.City;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Address {

	@Column(name = "address_cep")
	private String cep;

	@Column(name = "address_public_place")
	private String publicPlace;

	@Column(name = "address_number")
	private String number;

	@Column(name = "address_complement")
	private String complement;

	@Column(name = "address_district")
	private String district;

	@ManyToOne
	private City city;
}
