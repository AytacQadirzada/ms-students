package org.example.msstudents.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.example.msstudents.exception.NotFoundException;
import org.example.msstudents.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handler(NotFoundException exception) {
        return new ErrorResponse("Not_Found",
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse validation(ConstraintViolationException exception) {
        String errors = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        return new ErrorResponse(
                "Validation Failed",
                errors,
                HttpStatus.BAD_REQUEST.value()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationMetod(MethodArgumentNotValidException exception) {
//        String errors = exception.getConstraintViolations()
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.joining(", "));
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put("password",error.getDefaultMessage()));

        return new ErrorResponse(
                "Validation Failed",
                errors.get("password"),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneral(Exception exception) {
        exception.printStackTrace();
        return new ErrorResponse(
                "INTERNAL_ERROR",
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }

}
