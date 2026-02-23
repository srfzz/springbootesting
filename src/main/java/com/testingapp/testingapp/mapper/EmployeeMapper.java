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
    public void updateEntityFromDto(EmployeeRequestDto requestDto, EmployeeEntity entity) {
        if (requestDto == null || entity == null) {
            return;
        }
        entity.setFirstName(requestDto.firstName());
        entity.setLastName(requestDto.lastName());
        entity.setEmail(requestDto.email());
    }
    public void patchEntityFromDto(EmployeeRequestDto requestDto, EmployeeEntity entity) {
        if (requestDto == null || entity == null) {
            return;
        }

        // Only update if the field is not null
        if (requestDto.firstName() != null) {
            entity.setFirstName(requestDto.firstName());
        }
        if (requestDto.lastName() != null) {
            entity.setLastName(requestDto.lastName());
        }
        if (requestDto.email() != null) {
            entity.setEmail(requestDto.email());
        }
    }
}
