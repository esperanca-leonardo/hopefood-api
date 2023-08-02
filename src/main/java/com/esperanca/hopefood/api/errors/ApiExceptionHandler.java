package com.esperanca.hopefood.api.errors;

import com.esperanca.hopefood.domain.exceptions.BusinessLogicException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenInUseException;
import com.esperanca.hopefood.domain.exceptions.kitchen.KitchenNotFoundException;
import com.esperanca.hopefood.domain.exceptions.restaurant.RestaurantNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (isNull(body)) {
			body = Error.builder()
					.status(status.value())
					.timestamp(OffsetDateTime.now().withNano(0))
					.title(status.getReasonPhrase())
					.build();
		}
		else if (body instanceof String) {
			body = Error.builder()
					.status(status.value())
					.timestamp(OffsetDateTime.now().withNano(0))
					.title((String) body)
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		List<Field> fields = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError ->
					Field.builder()
							.name(fieldError.getField())
							.message(fieldError.getDefaultMessage())
							.build()
				).toList();

		final HttpStatus STATUS = BAD_REQUEST;
		final var ERROR = createErrorBuilder(STATUS.value(), INVALID_DATA,
				"One or more fields are invalid. Please fill in the correct " +
						"information and try again."
		).fields(fields).build();
		return handleExceptionInternal(ex, ERROR, headers, STATUS, request);
	}

	private Error.ErrorBuilder createErrorBuilder(Integer status, ErrorType type,
			String detail) {

		return Error.builder()
				.status(status)
				.timestamp(OffsetDateTime.now().withNano(0))
				.type(type.getPath())
				.title(type.getTitle())
				.detail(detail);
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

		final var STATUS = HttpStatus.NOT_FOUND;
		final var ERROR = createErrorBuilder(STATUS.value(), RESTAURANT_NOT_FOUND,
				exception.getMessage()
		).build();
		return handleExceptionInternal(exception, ERROR, new HttpHeaders(),
				STATUS, request
		);
	}
}
