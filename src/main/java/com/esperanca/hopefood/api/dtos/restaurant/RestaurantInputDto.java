package com.esperanca.hopefood.api.dtos.restaurant;

import java.math.BigDecimal;

public record RestaurantInputDto(
		String name,
		BigDecimal freightValue,
		Long kitchenId
) { }
