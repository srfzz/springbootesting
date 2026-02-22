package com.testingapp.testingapp.controller;

import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.reponse.ApiResponse;
import com.testingapp.testingapp.services.contracts.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto responseDto=employeeService.save(employeeRequestDto);

    }
}
