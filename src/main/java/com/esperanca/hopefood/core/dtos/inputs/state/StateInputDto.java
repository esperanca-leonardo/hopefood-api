package com.esperanca.hopefood.core.dtos.inputs.state;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class StateInputDto {

	@NotBlank
	private String name;
}
