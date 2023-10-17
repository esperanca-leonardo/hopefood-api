package com.esperanca.hopefood.domain.kitchen.exceptions;

import com.esperanca.hopefood.core.exceptions.EntityNotFoundException;

public class KitchenNotFoundException extends EntityNotFoundException {

	private static final String KITCHEN_NOT_FOUND =
			"Kitchen not found with ID: %d";

	public KitchenNotFoundException(String message) {
		super(message);
	}

	public KitchenNotFoundException(Long id) {
		this(String.format(KITCHEN_NOT_FOUND, id));
	}
}
