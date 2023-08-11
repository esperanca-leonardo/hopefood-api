package com.esperanca.hopefood.core.dtos.inputs.address;

import com.esperanca.hopefood.core.annotations.Exists;
import com.esperanca.hopefood.core.dtos.inputs.city.CityIdInputDto;
import com.esperanca.hopefood.domain.models.City;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AddressInputDto {

	@NotBlank
	private String cep;

	@NotBlank
	private String publicPlace;

	@NotBlank
	private String number;

	private String complement;

	@NotBlank
	private String district;

	@Valid
	@NotNull
	@Exists(targetClass = City.class)
	private CityIdInputDto city;
}
