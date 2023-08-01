package com.esperanca.hopefood.api.dtos.kitchens;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
public class KitchenInputDto {

	@NotBlank
	private String name;

	@NotBlank
	private String description;
}
