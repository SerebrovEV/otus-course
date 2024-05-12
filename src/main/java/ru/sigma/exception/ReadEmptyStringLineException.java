package ru.sigma.exception;

public class ReadEmptyStringLineException extends RuntimeException {
    public ReadEmptyStringLineException(String message) {
        super(message);
    }
}