package com.luken.levely.common.exception;

public class StartDateBeforeNowException extends RuntimeException {
    public StartDateBeforeNowException(String message) {
        super(message);
    }
}
