package com.esperanca.hopefood.domain.city.dtos.inputs;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CityIdInput {

	@NotNull
	@Positive
	private Long id;
}
