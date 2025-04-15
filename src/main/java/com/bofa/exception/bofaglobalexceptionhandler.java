package com.bofa.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class bofaglobalexceptionhandler {

    // Handle ResponseStatusException (for built-in exceptions)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleStatusException(ResponseStatusException ex) {
        return buildErrorResponse(ex.getReason(), ex.getStatusCode().value());
    }

    // Handle ServiceNotFoundException (Custom exception)
    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<Object> handleServiceNotFoundException(ServiceNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    // Handle ServiceAlreadyExistsException (Custom exception)
    @ExceptionHandler(ServiceAlreadyExistsException.class)
    public ResponseEntity<Object> handleServiceAlreadyExistsException(ServiceAlreadyExistsException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
    }

    // Helper method to build the error response
    private ResponseEntity<Object> buildErrorResponse(String reason, int statusCode) {
        Map<String, Object> errorRes = new LinkedHashMap<>();
        errorRes.put("status", statusCode);
        errorRes.put("error", reason);        
        return new ResponseEntity<>(errorRes, HttpStatus.valueOf(statusCode));
    }
}
