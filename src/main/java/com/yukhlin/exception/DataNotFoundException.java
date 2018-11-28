package com.yukhlin.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1292185396514136672L;

    public DataNotFoundException(String message) {
        super(message);
    }

}