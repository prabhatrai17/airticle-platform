package com.example.versioncontrolservice.exception;

import com.example.versioncontrolservice.Dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomApiExceptionHandler {
    @ExceptionHandler(GlobalExceptionHandler.class)
    public ResponseEntity<ApiResponseDto> customApiExceptionHandler(GlobalExceptionHandler exceptionHandler) {
        return new ResponseEntity<>(ApiResponseDto.builder()
                .message(exceptionHandler.getMessage())
                .success(true)
                .build(), HttpStatus.CREATED);
    }
}
