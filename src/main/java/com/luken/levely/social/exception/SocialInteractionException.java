package com.luken.levely.social.exception;

import com.luken.levely.common.exception.controller.ApiError;
import lombok.Getter;

@Getter
public class SocialInteractionException extends RuntimeException {

    ApiError apiError;

    public SocialInteractionException(String message, ApiError apiError) {
        super(message);
        this.apiError = apiError;
    }
}
