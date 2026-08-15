package com.luken.levely.common.exception;

import com.luken.levely.common.exception.controller.ApiError;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    ApiError apiError;

    public ResourceNotFoundException(String message, ApiError apiError) {
        super(message);
        this.apiError = apiError;
    }
}
