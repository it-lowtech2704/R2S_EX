package com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest req){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(java.util.Map.of(
                "status",401,
                "error","Unauthorized",
                "message",ex.getMessage(),
                "path",req.getDescription(false).replace("uri=","")
        ));
    }
}
