package com.testingapp.testingapp.controller;

import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.reponse.ApiResponse;
import com.testingapp.testingapp.services.contracts.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto responseDto=employeeService.save(employeeRequestDto);
        ApiResponse<?> apiResponse=ApiResponse.builder().success(true).status(HttpStatus.OK.value()).message("Employee Created Successfully !").data(responseDto).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
}
