package com.aitorgonzalez.challenge.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException {
	private static final long serialVersionUID = 8563759831034885388L;

	private final Map<String, String> fieldErrors = new HashMap<>();

	public InvalidRequestException(Errors errors) {
		for (FieldError error : errors.getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}
	}
}