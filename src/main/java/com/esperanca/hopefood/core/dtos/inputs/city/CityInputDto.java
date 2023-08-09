package com.esperanca.hopefood.core.dtos.inputs.city;

import com.esperanca.hopefood.core.dtos.inputs.state.StateIdInputDto;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CityInputDto {

	@NotBlank
	private String name;

	@Valid
	@NotNull
	private StateIdInputDto state;
}
