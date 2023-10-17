package com.esperanca.hopefood.domain.address.dtos.outputs;

import com.esperanca.hopefood.domain.city.dtos.outputs.CityModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {
	private String cep;
	private String publicPlace;
	private String number;
	private String complement;
	private String district;
	private CityModel city;
}
