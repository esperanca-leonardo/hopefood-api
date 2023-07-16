package com.esperanca.hopefood.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class EntityNotFoundException extends BusinessLogicException {

	public EntityNotFoundException(String message) {
		super(message);
	}
}
