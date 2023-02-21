package com.idstar.lms.api.components;

import com.idstar.lms.api.exceptions.InvalidTokenException;
import com.idstar.lms.api.exceptions.UserAlreadyExistsException;
import com.idstar.lms.api.exceptions.UserNotExistsException;
import com.idstar.lms.api.constants.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserExceptionHandler {

    @ResponseBody
    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<ResponseMapper> guitarNotExist(InvalidTokenException e) {
        return ResponseEntity.badRequest().body(
                new ResponseMapper(HttpStatus.UNAUTHORIZED.value(),
                        "invalid refresh token", e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(UserNotExistsException.class)
    ResponseEntity<ResponseMapper> userNotExist(UserNotExistsException e) {
        return ResponseEntity.badRequest().body(
                new ResponseMapper(HttpStatus.BAD_REQUEST.value(),
                        "user: " + e.getMessage() + " doesn't exists", e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<ResponseMapper> userAlreadyExist(UserAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(
                new ResponseMapper(HttpStatus.BAD_REQUEST.value(),
                        "user: " + e.getMessage() + " already exists", e.getMessage()));
    }
}