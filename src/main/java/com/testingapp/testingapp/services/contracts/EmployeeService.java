package com.testingapp.testingapp.services.contracts;

import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface EmployeeService {
    List<EmployeeResponseDto> findAll();
    EmployeeResponseDto findById(Long id);
    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto, Long id);
    EmployeeResponseDto UpdateEmployee(Map<String, Object> employeeRequestDto, Long id);
    void delete(Long id);
}
