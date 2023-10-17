package com.esperanca.hopefood.domain.kitchen.dtos.inputs;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
public class KitchenInput {

	@NotBlank
	private String name;

	@NotBlank
	private String description;
}
