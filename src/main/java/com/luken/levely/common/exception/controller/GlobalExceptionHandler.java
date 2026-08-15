package com.luken.levely.common.exception.controller;

import com.luken.levely.common.exception.*;
import com.luken.levely.setlog.exception.SetRepInvalidException;
import com.luken.levely.setlog.exception.SetRepWeightInvalidException;
import com.luken.levely.social.exception.SocialInteractionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // NOT FOUND

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionStandart> handlerResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {

        ExceptionStandart error = new ExceptionStandart(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                ex.getApiError(),
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // BAD REQUEST

    @ExceptionHandler(StartDateBeforeNowException.class)
    public ResponseEntity<ExceptionStandart> handlerStartDateBeforeNowException(StartDateBeforeNowException ex, HttpServletRequest request) {

        Map<String, Object> details = Map.of(
                "actualDate", ex.getNowDate(),
                "available", ex.getStartDate()
        );

        ExceptionStandart error = new ExceptionStandart(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                ex.getApiError(),
                ex.getMessage(),
                request.getRequestURI(),
                details
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EndDateBeforeStartDateException.class)
    public ResponseEntity<ExceptionStandart> handlerEndDateBeforeStartDateException(EndDateBeforeStartDateException ex, HttpServletRequest request) {

        Map<String, Object> details = Map.of(
                "availableEndDate", ex.getEndate(),
                "requiredEndDate", ex.getStartDate()
        );

        ExceptionStandart error = new ExceptionStandart(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                ex.getApiError(),
                ex.getMessage(),
                request.getRequestURI(),
                details
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SocialInteractionException.class)
    public ResponseEntity<ExceptionStandart> handlerSocialInteractionException(SocialInteractionException ex, HttpServletRequest request) {

        ExceptionStandart error = new ExceptionStandart(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                ex.getApiError(),
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SetRepInvalidException.class)
    public ResponseEntity<ExceptionStandart> handlerSetRepInvalidException(SetRepInvalidException ex, HttpServletRequest request) {

        Map<String, Object> details = Map.of(
                "availableReps", ex.getAvailableRep(),
                "requiredReps", ex.getRequiredRep()
        );

        ExceptionStandart error = new ExceptionStandart(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                ex.getApiError(),
                ex.getMessage(),
                request.getRequestURI(),
                details
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SetRepWeightInvalidException.class)
    public ResponseEntity<ExceptionStandart> handlerSetRepWeightInvalidException(SetRepWeightInvalidException ex, HttpServletRequest request) {

        Map<String, Object> details = Map.of(
                "availableWeight", ex.getAvailableWeight(),
                "requiredWeight", ex.getRequiredWeight()
        );

        ExceptionStandart error = new ExceptionStandart(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                ex.getApiError(),
                ex.getMessage(),
                request.getRequestURI(),
                details
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // UNAUTHORIZED

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionStandart> handlerUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {

        ExceptionStandart error = new ExceptionStandart(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED,
                ex.getApiError(),
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
