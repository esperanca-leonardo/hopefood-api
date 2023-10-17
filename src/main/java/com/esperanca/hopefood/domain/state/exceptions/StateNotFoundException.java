package com.esperanca.hopefood.domain.state.exceptions;

import com.esperanca.hopefood.core.exceptions.EntityNotFoundException;

public class StateNotFoundException extends EntityNotFoundException {
	private static final String STATE_NOT_FOUND =
			"State not found with ID: %d";

	public StateNotFoundException(String message) {
		super(message);
	}

	public StateNotFoundException(Long id) {
		this(String.format(STATE_NOT_FOUND, id));
	}
}
