package com.testingapp.testingapp.services.Impl;


import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.entity.EmployeeEntity;
import com.testingapp.testingapp.mapper.EmployeeMapper;
import com.testingapp.testingapp.repository.EmployeeRepository;
import com.testingapp.testingapp.services.contracts.EmployeeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.List;

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
    public List<EmployeeResponseDto> findAll() {
        return List.of();
    }

    @Override
    public EmployeeResponseDto findById(Long id) {
        return null;
    }

    @Override
    @Transactional()
    public EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto) {
        try{
            EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeRequestDto);
            EmployeeEntity savedEntity = employeeRepository.saveAndFlush(employeeEntity);
            return employeeMapper.toDto(savedEntity);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public EmployeeResponseDto update(EmployeeRequestDto employeeRequestDto, Long id) {
        return null;
    }

    @Override
    public EmployeeResponseDto UpdateEmployee(EmployeeRequestDto employeeRequestDto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
