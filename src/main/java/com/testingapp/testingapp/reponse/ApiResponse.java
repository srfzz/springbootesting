package com.testingapp.testingapp.reponse;

import com.testingapp.testingapp.dto.EmployeeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse<T> {
    private boolean success;
    private Integer status;
    private String message;
    private  T data;
    private LocalDateTime timestamp;
}
