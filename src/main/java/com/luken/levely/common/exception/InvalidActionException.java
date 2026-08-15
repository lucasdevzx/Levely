package com.luken.levely.common.exception;

import com.luken.levely.common.exception.controller.ApiError;

public class InvalidActionException extends RuntimeException{

    ApiError apiError;

    public InvalidActionException(String message, ApiError apiError) {
        super(message);
        this.apiError = apiError;
    }
}
