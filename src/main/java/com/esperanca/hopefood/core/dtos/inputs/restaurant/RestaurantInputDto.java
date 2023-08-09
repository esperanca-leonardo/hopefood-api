package com.esperanca.hopefood.core.dtos.inputs.restaurant;

import com.esperanca.hopefood.core.dtos.inputs.address.AddressInputDto;
import com.esperanca.hopefood.core.dtos.inputs.kitchen.KitchenIdInputDto;
import com.esperanca.hopefood.core.dtos.outputs.address.AddressOutputDto;
import com.esperanca.hopefood.core.dtos.outputs.city.CityOutputDto;
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
	private AddressInputDto address;

	@Valid
	@NotNull
	private KitchenIdInputDto kitchen;
}