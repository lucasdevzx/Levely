package com.luken.levely.common.exception;

import com.luken.levely.controller.exception.ApiError;
import lombok.Getter;

@Getter
public class SetRepWeightInvalidException extends RuntimeException {

    ApiError apiError;
    Double availableWeight;
    Double requiredWeight;

    public SetRepWeightInvalidException(String message, ApiError apiError, Double availableWeight, Double requiredWeight) {
        super(message);
        this.apiError = apiError;
        this.availableWeight = availableWeight;
        this.requiredWeight = requiredWeight;
    }
}
