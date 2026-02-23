package com.testingapp.testingapp.reponse;

import com.testingapp.testingapp.dto.EmployeeResponseDto;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private Integer status;
    private String message;
    private  T data;
    private LocalDateTime timestamp;
}
