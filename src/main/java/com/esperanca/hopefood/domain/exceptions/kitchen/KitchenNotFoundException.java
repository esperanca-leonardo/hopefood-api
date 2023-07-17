package com.esperanca.hopefood.domain.exceptions.kitchen;

import com.esperanca.hopefood.domain.exceptions.EntityNotFoundException;

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
