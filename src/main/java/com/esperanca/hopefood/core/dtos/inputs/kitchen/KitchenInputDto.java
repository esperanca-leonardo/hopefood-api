package com.esperanca.hopefood.core.dtos.inputs.kitchen;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
public class KitchenInputDto {

	@NotBlank
	private String name;

	@NotBlank
	private String description;
}
