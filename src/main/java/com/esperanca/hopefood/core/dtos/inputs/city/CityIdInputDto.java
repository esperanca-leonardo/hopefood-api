package com.esperanca.hopefood.core.dtos.inputs.city;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CityIdInputDto {

	@NotNull
	@Positive
	private Long id;
}
