package com.itaitan.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidantionError extends StandardError {
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidantionError() {
		super();
	}

	public ValidantionError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
		
		
}
