package com.luken.levely.common.exception;

import com.luken.levely.controller.exception.ApiError;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EndDateBeforeStartDateException extends RuntimeException {

    ApiError apiError;
    LocalDate startDate;
    LocalDate endate;

    public EndDateBeforeStartDateException(String message, ApiError apiError, LocalDate startDate, LocalDate endate) {
        super(message);
        this.apiError = apiError;
        this.startDate = startDate;
        this.endate = endate;
    }
}
