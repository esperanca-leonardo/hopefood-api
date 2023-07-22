package com.esperanca.hopefood.api.dtos.restaurant;

import com.esperanca.hopefood.api.dtos.kitchens.KitchenOutputDto;

import java.math.BigDecimal;

public record RestaurantOutputDto(
		Long id,
		String name,
		BigDecimal freightValue,
		KitchenOutputDto kitchenOutputDto
) { }
