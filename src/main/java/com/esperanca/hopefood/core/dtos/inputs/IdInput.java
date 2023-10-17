package com.esperanca.hopefood.core.dtos.inputs;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public abstract class IdInput extends Input {

	@NotNull
	@Positive
	protected Long id;
}
