package com.esperanca.hopefood.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class BusinessLogicException extends RuntimeException {

	public BusinessLogicException(String message) {
		super(message);
	}
}
