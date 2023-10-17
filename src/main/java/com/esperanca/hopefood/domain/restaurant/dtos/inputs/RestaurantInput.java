package com.esperanca.hopefood.domain.restaurant.dtos.inputs;

import com.esperanca.hopefood.core.annotations.exists.Exists;
import com.esperanca.hopefood.domain.address.dtos.inputs.AddressInput;
import com.esperanca.hopefood.domain.kitchen.dtos.inputs.KitchenIdInput;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
public class RestaurantInput {

	@NotBlank
	private String name;

	@NotNull
	@PositiveOrZero
	private BigDecimal freightValue;

	@Valid
	@NotNull
	private AddressInput address;

	@Valid
	@Exists
	@NotNull
	private KitchenIdInput kitchen;
}
