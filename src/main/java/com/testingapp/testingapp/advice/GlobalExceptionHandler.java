package com.testingapp.testingapp.advice;



import com.testingapp.testingapp.exceptions.ResourceAlreadyExistException;
import com.testingapp.testingapp.exceptions.ResourceNotFoundException;
import com.testingapp.testingapp.reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex)
    {
   ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("An unexpected error occurred. Please try again later.").data(null).timestamp(LocalDateTime.now()).build();
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
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceAlreadyExistsException(ResourceAlreadyExistException ex)
    {
        ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).data(null).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiReponse);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).data(null).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiReponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex)
    {
        ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.BAD_REQUEST.value()).message("Required request body is missing or the JSON is malformed").data(null).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiReponse);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex)
    {
        ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).data(null).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiReponse);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex)
    {
        ApiResponse<?> apiReponse=ApiResponse.builder().success(false).status(HttpStatus.METHOD_NOT_ALLOWED.value()).message(ex.getMessage()).data(null).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiReponse);
    }
}
