package com.esperanca.hopefood.domain.state.dtos.inputs;

import com.esperanca.hopefood.core.dtos.inputs.Input;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class StateInput extends Input {

	@NotBlank
	private String name;
}
