package com.backend.project.controller.advice.exceptions;

public class NullIDException extends RuntimeException {
    public NullIDException(String message) {
        super(message);
    }
}
