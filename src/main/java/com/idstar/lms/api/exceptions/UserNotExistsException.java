package com.idstar.lms.api.exceptions;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String username) {
        super(username);
    }
}