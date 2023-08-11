package com.esperanca.hopefood.core.dtos.inputs.city;

import com.esperanca.hopefood.core.annotations.Exists;
import com.esperanca.hopefood.core.dtos.inputs.state.StateIdInputDto;
import com.esperanca.hopefood.domain.models.State;
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
	@Exists(targetClass = State.class)
	private StateIdInputDto state;
}
