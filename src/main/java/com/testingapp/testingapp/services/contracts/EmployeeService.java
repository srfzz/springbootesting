package com.testingapp.testingapp.services.contracts;

import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> findAll();
    EmployeeResponseDto findById(Long id);
    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto, Long id);
    EmployeeResponseDto UpdateEmployee(EmployeeRequestDto employeeRequestDto, Long id);
    void delete(Long id);
}
