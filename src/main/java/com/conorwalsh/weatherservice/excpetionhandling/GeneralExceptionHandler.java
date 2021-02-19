package com.conorwalsh.weatherservice.excpetionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * General exception handling class.
 */
@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String ERROR_MESSAGE_TEMPLATE = "message: %s %n requested uri: %s";
	public static final String LIST_JOIN_DELIMITER = ",";
	private static final Logger local_logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);
	private static final String ERRORS_FOR_PATH = "errors {} for path {}";
	private static final String PATH = "path";
	private static final String ERRORS = "error";
	private static final String STATUS = "status";
	private static final String MESSAGE = "message";
	private static final String TIMESTAMP = "timestamp";
	private static final String TYPE = "type";

	
	/**
	 * A general handler for all HTTPClientError exceptions
	 */
	@ExceptionHandler({HttpClientErrorException.class})
	public ResponseEntity<Object> handleAllExceptions(HttpClientErrorException exception, WebRequest request) {
		final HttpStatus status = exception!=null ? exception.getStatusCode():HttpStatus.INTERNAL_SERVER_ERROR;
		final String localizedMessage = exception.getLocalizedMessage();
		final String path = request.getDescription(false);
		String message = (!localizedMessage.isEmpty() ? localizedMessage:status.getReasonPhrase());
		logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
	}

	
	/**
	 * A general handler for all uncaught exceptions
	 */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		System.out.println("other cATCH");
		ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
		final HttpStatus status = responseStatus!=null ? responseStatus.value():HttpStatus.INTERNAL_SERVER_ERROR;
		final String localizedMessage = exception.getLocalizedMessage();
		final String path = request.getDescription(false);
		String message = ((localizedMessage!=null && !localizedMessage.isEmpty()) ? localizedMessage:status.getReasonPhrase());
		logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
		return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
	}

	/**
	 * Build a detailed information about the exception in the response
	 */
	private ResponseEntity<Object> getExceptionResponseEntity(final Exception exception,
								  final HttpStatus status,
								  final WebRequest request,
								  final List<String> errors) {
		final Map<String, Object> body = new LinkedHashMap<>();
		final String path = request.getDescription(false);
		body.put(TIMESTAMP, Instant.now());
		body.put(STATUS, status.value());
		body.put(ERRORS, errors);
		body.put(TYPE, exception.getClass().getSimpleName());
		body.put(PATH, path);
		body.put(MESSAGE, status.getReasonPhrase());
		final String errorsMessage = (errors != null && errors.size()>0) ?
				errors.stream().collect(Collectors.joining(LIST_JOIN_DELIMITER))
				:status.getReasonPhrase();
		local_logger.error(ERRORS_FOR_PATH, errorsMessage, path);
		return new ResponseEntity<>(body, status);
	}

	
}
