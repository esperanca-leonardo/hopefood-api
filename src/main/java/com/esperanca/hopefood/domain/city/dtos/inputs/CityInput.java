package com.esperanca.hopefood.domain.city.dtos.inputs;

import com.esperanca.hopefood.core.annotations.exists.Exists;
import com.esperanca.hopefood.domain.state.dtos.inputs.StateIdInput;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CityInput {

	@NotBlank
	private String name;

	@Valid
	@Exists
	@NotNull
	private StateIdInput state;
}
