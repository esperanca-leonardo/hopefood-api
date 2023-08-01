package com.esperanca.hopefood.api.dtos.restaurant;

import com.esperanca.hopefood.api.dtos.kitchens.KitchenOutputDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantOutputDto {
	private Long id;
	private String name;
	private BigDecimal freightValue;
	private KitchenOutputDto kitchen;
}
