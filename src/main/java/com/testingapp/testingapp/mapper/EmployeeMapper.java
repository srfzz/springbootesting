package com.testingapp.testingapp.mapper;

import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component

public class EmployeeMapper {
    public EmployeeEntity toEntity(EmployeeRequestDto requestDto)
    {
        if(requestDto==null)
        {
            return null;
        }
        return EmployeeEntity.builder().firstName(requestDto.firstName()).lastName(requestDto.lastName()).email(requestDto.email()).build();
    }
    public EmployeeResponseDto toDto(EmployeeEntity entity)
    {
        if(entity==null)
        {
            return null;
        }
        return EmployeeResponseDto.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName()).email(entity.getEmail()).build();
    }
}
