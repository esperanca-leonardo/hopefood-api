package com.esperanca.hopefood.api.errors;

import com.esperanca.hopefood.domain.exceptions.BusinessLogicException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.exceptions.restaurant.RestaurantNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import static com.esperanca.hopefood.api.errors.ErrorType.*;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private List<Field> extractFieldErrors(MethodArgumentNotValidException exception) {
		return exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(this::mapToField)
				.toList();
	}

	private Field mapToField(FieldError fieldError) {
		return Field.builder()
				.name(fieldError.getField())
				.message(fieldError.getDefaultMessage())
				.build();
	}

	private OffsetDateTime createTimestampWithZeroNanos() {
		return OffsetDateTime.now().withNano(0);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (isNull(body)) {
			body = Error.builder()
					.status(status.value())
					.title(status.getReasonPhrase())
					.timestamp(createTimestampWithZeroNanos())
					.build();
		}
		else if (body instanceof String) {
			body = Error.builder()
					.title((String) body)
					.status(status.value())
					.timestamp(createTimestampWithZeroNanos())
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		final HttpStatus STATUS = BAD_REQUEST;
		final List<Field> FIELDS = extractFieldErrors(ex);
		final var DETAIL = "One or more fields are invalid. Please fill in " +
				"the correct information and try again.";
		final var ERROR = createErrorBuilder(STATUS.value(), INVALID_DATA, DETAIL)
				.fields(FIELDS).build();

		return handleExceptionInternal(ex, ERROR, headers, STATUS, request);
	}

	private Error.ErrorBuilder createErrorBuilder(Integer status, ErrorType type,
			String detail) {

		return Error.builder()
				.status(status)
				.detail(detail)
				.type(type.getPath())
				.title(type.getTitle())
				.timestamp(createTimestampWithZeroNanos());
	}

	@ExceptionHandler(KitchenNotFoundException.class)
	public ResponseEntity<?> handleKitchenNotFound(WebRequest request,
			KitchenNotFoundException exception) {

		final HttpStatus STATUS = NOT_FOUND;
		final var ERROR = createErrorBuilder(STATUS.value(), KITCHEN_NOT_FOUND,
				exception.getMessage()
		).build();

		return handleExceptionInternal(exception, ERROR, new HttpHeaders(),
				STATUS, request
		);
	}

	@ExceptionHandler(KitchenInUseException.class)
	public ResponseEntity<?> handleKitchenInUse(WebRequest request,
			KitchenInUseException exception) {

		final HttpStatus STATUS = CONFLICT;
		final var ERROR = createErrorBuilder(STATUS.value(), KITCHEN_IN_USE,
				exception.getMessage()
		).build();

		return handleExceptionInternal(exception, ERROR, new HttpHeaders(),
				STATUS, request
		);
	}

	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<?> handleBusinessLogic(WebRequest request,
			BusinessLogicException exception) {

		final HttpStatus STATUS = BAD_REQUEST;
		final var ERROR = createErrorBuilder(STATUS.value(), BUSINESS_LOGIC,
				exception.getMessage()
		).build();

		return handleExceptionInternal(exception, ERROR, new HttpHeaders(),
				STATUS, request
		);
	}

	@ExceptionHandler(RestaurantNotFoundException.class)
	public ResponseEntity<?> handleRestaurantNotFound(WebRequest request,
			RestaurantNotFoundException exception) {

		final HttpStatus STATUS = NOT_FOUND;
		final var ERROR = createErrorBuilder(STATUS.value(), RESTAURANT_NOT_FOUND,
				exception.getMessage()
		).build();

		return handleExceptionInternal(exception, ERROR, new HttpHeaders(),
				STATUS, request
		);
	}
}
