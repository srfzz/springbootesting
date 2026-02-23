package com.testingapp.testingapp.advice;

import com.testingapp.testingapp.reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex)
    {
   ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).data(null).timestamp(LocalDateTime.now()).build();
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiReponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage,(existing, replacement) -> existing));
        ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.BAD_REQUEST.value()).message("Validation Error").data(errors).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiReponse);
    }
}
