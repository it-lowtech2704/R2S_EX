package com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class BadRequestException extends  RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                java.util.Map.of(
                        "status", 400,
                        "error", "Bad Request",
                        "message", ex.getMessage(),
                        "path", request.getDescription(false).replace("uri=", "")
                )
        );
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
