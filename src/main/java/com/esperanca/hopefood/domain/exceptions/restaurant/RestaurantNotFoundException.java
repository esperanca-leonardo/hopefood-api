package com.esperanca.hopefood.domain.exceptions.restaurant;

import com.esperanca.hopefood.domain.exceptions.EntityNotFoundException;

public class RestaurantNotFoundException extends EntityNotFoundException {

	private final static String RESTAURANT_NOT_FOUND =
			"Restaurant not found with ID: %d";

	public RestaurantNotFoundException(String message) {
		super(message);
	}

	public RestaurantNotFoundException(Long id) {
		this(String.format(RESTAURANT_NOT_FOUND, id));
	}
}
