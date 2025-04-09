package com.bofa.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class EmployeeGlobalException {
    public EmployeeGlobalException() {
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleStatusException(ResponseStatusException e) {
        return buildErrorResponse(e.getReason(),e.getStatusCode().value());
    }
    private ResponseEntity<Object> buildErrorResponse(String reason, int statusCode) {
        Map<String, Object> errorResponse = new LinkedHashMap<String, Object>();
        errorResponse.put("Status", statusCode);
        errorResponse.put("error", reason);
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(statusCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        return ResponseEntity.internalServerError().body("Error occured:"+ex.getMessage());
    }
}
