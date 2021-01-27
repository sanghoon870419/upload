package com.spring.upload.exception;


public class IllegalFileFormatException extends RuntimeException {
    public IllegalFileFormatException(String message) {
        super(message);
    }
}
