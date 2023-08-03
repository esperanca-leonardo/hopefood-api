package com.esperanca.hopefood.core.dtos.kitchens;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class KitchenIdInputDto {

	@NotNull
	@Positive
	private Long id;
}