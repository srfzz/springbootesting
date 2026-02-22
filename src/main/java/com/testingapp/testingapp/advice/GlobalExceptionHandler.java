package com.testingapp.testingapp.advice;

import com.testingapp.testingapp.reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex)
    {
   ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).data(null).timestamp(LocalDateTime.now()).build();
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiReponse);
    }
}
