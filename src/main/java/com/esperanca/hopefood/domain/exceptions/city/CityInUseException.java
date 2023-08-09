package com.esperanca.hopefood.domain.exceptions.city;

import com.esperanca.hopefood.domain.exceptions.EntityInUseException;

public class CityInUseException extends EntityInUseException {

	private static final String CITY_IN_USE = "City in use with ID: %d";

	public CityInUseException(String message) {
		super(message);
	}

	public CityInUseException(Long id) {
		this(String.format(CITY_IN_USE, id));
	}
}
