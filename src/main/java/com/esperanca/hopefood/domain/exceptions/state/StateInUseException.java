package com.esperanca.hopefood.domain.exceptions.state;

import com.esperanca.hopefood.domain.exceptions.EntityInUseException;

public class StateInUseException extends EntityInUseException {

	private static final String STATE_IN_USE = "State in use with ID: %d";

	public StateInUseException(String message) {
		super(message);
	}

	public StateInUseException(Long id) {
		this(String.format(STATE_IN_USE, id));
	}
}
