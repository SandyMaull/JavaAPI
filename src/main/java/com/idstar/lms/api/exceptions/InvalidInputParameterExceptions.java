package com.idstar.lms.api.exceptions;

public class InvalidInputParameterExceptions extends RuntimeException {
    public InvalidInputParameterExceptions(String parameter) {
        super("Invalid input parameter: " + parameter);
    }
}