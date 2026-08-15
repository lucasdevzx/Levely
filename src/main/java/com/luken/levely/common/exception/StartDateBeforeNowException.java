package com.luken.levely.common.exception;

import com.luken.levely.common.exception.controller.ApiError;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StartDateBeforeNowException extends RuntimeException {

    ApiError apiError;
    LocalDate startDate;
    LocalDate nowDate;

    public StartDateBeforeNowException(String message, ApiError apiError, LocalDate startDate, LocalDate  nowDate) {
        super(message);
        this.apiError = apiError;
        this.startDate = startDate;
        this.nowDate = nowDate;
    }
}
