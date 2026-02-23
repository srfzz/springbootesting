package com.testingapp.testingapp.services.Impl;


import java.util.List;
import java.util.stream.Collectors;

import com.openthinks.others.webpages.exception.ResourceAlreadyExistException;
import com.testingapp.testingapp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.entity.EmployeeEntity;
import com.testingapp.testingapp.mapper.EmployeeMapper;
import com.testingapp.testingapp.repository.EmployeeRepository;
import com.testingapp.testingapp.services.contracts.EmployeeService;

import tools.jackson.databind.cfg.MapperBuilder;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final MapperBuilder mapperBuilder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, MapperBuilder mapperBuilder) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.mapperBuilder = mapperBuilder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> findAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto findById(Long id) {

        return employeeRepository.findById(id).map(employeeMapper::toDto).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
    }

    @Override
    @Transactional()
    public EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto) {
        boolean isExist = employeeRepository.existsByEmail(employeeRequestDto.email());
        if(isExist){
            throw new ResourceAlreadyExistException("Employee with email " + employeeRequestDto.email() + " already exists.");
        }
            EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeRequestDto);
            EmployeeEntity savedEntity = employeeRepository.saveAndFlush(employeeEntity);
            return employeeMapper.toDto(savedEntity);
       
    }

    
    @Override
    public EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto, Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
        EmployeeEntity savedEntity= employeeRepository.save(employeeMapper.toEntity(employeeRequestDto));
        return employeeMapper.toDto(savedEntity);
    }

    @Override
    public EmployeeResponseDto UpdateEmployee(EmployeeRequestDto employeeRequestDto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
