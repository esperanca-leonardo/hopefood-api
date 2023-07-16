package com.esperanca.hopefood.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class EntityInUseException extends BusinessLogicException {

	public EntityInUseException(String message) {
		super(message);
	}
}
