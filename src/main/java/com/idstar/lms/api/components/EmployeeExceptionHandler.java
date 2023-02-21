package com.idstar.lms.api.components;

import com.idstar.lms.api.exceptions.EmployeeNotExistsException;
import com.idstar.lms.api.constants.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ResponseBody
    @ExceptionHandler(EmployeeNotExistsException.class)
    ResponseEntity<ResponseMapper> guitarNotExist(EmployeeNotExistsException e) {
        return ResponseEntity.badRequest().body(
                new ResponseMapper(HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(), e.getCause()));
    }
}
