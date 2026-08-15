package com.luken.levely.setlog.exception;

import com.luken.levely.common.exception.controller.ApiError;
import lombok.Getter;

@Getter
public class SetRepInvalidException extends RuntimeException {

    ApiError apiError;
    Integer availableRep;
    Integer requiredRep;

    public SetRepInvalidException(String message, ApiError apiError, Integer availableRep, Integer requiredRep) {
        super(message);
        this.apiError = apiError;
        this.availableRep = availableRep;
        this.requiredRep = requiredRep;
    }
}
