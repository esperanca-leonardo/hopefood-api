package com.esperanca.hopefood.core.dtos.inputs.state;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class StateIdInputDto {

	@NotNull
	@Positive
	private Long id;
}
