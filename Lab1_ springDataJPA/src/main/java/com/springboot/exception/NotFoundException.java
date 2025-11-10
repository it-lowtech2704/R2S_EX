package com.springboot.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Resource not found with id = " + id);
    }
}
