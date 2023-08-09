package com.esperanca.hopefood.core.dtos.outputs.address;

import com.esperanca.hopefood.core.dtos.outputs.city.CityOutputDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutputDto {
	private String cep;
	private String publicPlace;
	private String number;
	private String complement;
	private String district;
	private CityOutputDto city;
}
