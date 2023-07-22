package com.esperanca.hopefood.domain.exceptions;

public class EntityInUseException extends BusinessLogicException {

	public EntityInUseException(String message) {
		super(message);
	}
}
