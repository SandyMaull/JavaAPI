package com.idstar.lms.api.exceptions;

public class EmployeeNotExistsException extends RuntimeException {
    public EmployeeNotExistsException(String message) {
        super(message);
    }
}
