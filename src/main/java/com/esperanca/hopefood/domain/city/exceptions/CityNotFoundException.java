package com.esperanca.hopefood.domain.city.exceptions;

import com.esperanca.hopefood.core.exceptions.EntityNotFoundException;

public class CityNotFoundException extends EntityNotFoundException {

	private final static String CITY_NOT_FOUND = "City not found with ID: %d";

	public CityNotFoundException(String message) {
		super(message);
	}

	public CityNotFoundException(Long id) {
		this(String.format(CITY_NOT_FOUND, id));
	}
}
