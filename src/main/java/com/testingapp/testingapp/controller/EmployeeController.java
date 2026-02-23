package com.testingapp.testingapp.controller;

import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.reponse.ApiResponse;
import com.testingapp.testingapp.services.contracts.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
        ApiResponse<?> apiResponse=ApiResponse.builder().success(true).status(HttpStatus.CREATED.value()).message("Employee Created Successfully !").data(responseDto).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }
    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getAll() {
        List<EmployeeResponseDto> responseDto=employeeService.findAll();
        ApiResponse<?> apiResponse=ApiResponse.builder().status(HttpStatus.OK.value()).success(true).timestamp(LocalDateTime.now()).message("Employee data").data(responseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable Long id) {
        EmployeeResponseDto responseDto=employeeService.findById(id);
        ApiResponse<?> apiResponse=ApiResponse.builder().status(HttpStatus.OK.value()).success(true).timestamp(LocalDateTime.now()).message("Employee data").data(responseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto responseDto=employeeService.update(employeeRequestDto,id);
        ApiResponse<?> apiResponse=ApiResponse.builder().status(HttpStatus.OK.value()).success(true).timestamp(LocalDateTime.now()).message("Employee Updated Successfully").data(responseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
