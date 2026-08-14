package com.luken.levely.common.exception;

import com.luken.levely.controller.exception.ApiError;
import lombok.Getter;

@Getter
public class SocialInteractionException extends RuntimeException {

    ApiError apiError;

    public SocialInteractionException(String message, ApiError apiError) {
        super(message);
        this.apiError = apiError;
    }
}
