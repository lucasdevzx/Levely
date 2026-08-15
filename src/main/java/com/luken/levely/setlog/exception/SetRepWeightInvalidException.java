package com.luken.levely.setlog.exception;

import com.luken.levely.common.exception.controller.ApiError;
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
