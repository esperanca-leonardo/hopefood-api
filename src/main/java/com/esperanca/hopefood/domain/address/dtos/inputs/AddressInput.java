package com.esperanca.hopefood.domain.address.dtos.inputs;

import com.esperanca.hopefood.domain.city.dtos.inputs.CityIdInput;
import com.esperanca.hopefood.core.annotations.exists.Exists;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AddressInput {

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
	@Exists
	@NotNull
	private CityIdInput city;
}
