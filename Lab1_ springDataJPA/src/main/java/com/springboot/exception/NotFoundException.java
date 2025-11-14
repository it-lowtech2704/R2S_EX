package com.springboot.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(int id) {
        super(msg(id));
    }

    public NotFoundException(Long id) {
        super(msg(id));
    }

    public NotFoundException(String message) { // dùng khi bạn muốn tự ghi chú
        super(message);
    }

    private static String msg(Object id) {
        return "Resource not found with id = " + String.valueOf(id);
    }
}
