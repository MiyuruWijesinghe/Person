package com.abc.person.exception;

public class ValidateRecordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String field;

	public ValidateRecordException(String exception, String field) {
		super(exception);
		this.field = field;
	}

	public String getField() {
		return this.field;
	}
}
