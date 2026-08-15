package com.luken.levely.common.exception.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class ExceptionStandart {
    LocalDateTime timestamp;
    HttpStatus httpStatus;
    ApiError apiError;
    String message;
    String path;
    Object details;
}
