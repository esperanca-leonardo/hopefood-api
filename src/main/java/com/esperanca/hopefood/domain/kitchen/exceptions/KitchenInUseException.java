package com.esperanca.hopefood.domain.kitchen.exceptions;

import com.esperanca.hopefood.core.exceptions.EntityInUseException;

public class KitchenInUseException extends EntityInUseException {

	private static final String KITCHEN_IN_USE = "Kitchen in use with ID: %d";

	public KitchenInUseException(String message) {
		super(message);
	}

	public KitchenInUseException(Long id) {
		this(String.format(KITCHEN_IN_USE, id));
	}
}
