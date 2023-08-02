package com.esperanca.hopefood.core.dtos.restaurant;

import com.esperanca.hopefood.core.dtos.kitchens.KitchenIdInputDto;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
public class RestaurantInputDto {

	@NotBlank
	private String name;

	@NotNull
	@PositiveOrZero
	private BigDecimal freightValue;

	@Valid
	@NotNull
	private KitchenIdInputDto kitchen;
}
