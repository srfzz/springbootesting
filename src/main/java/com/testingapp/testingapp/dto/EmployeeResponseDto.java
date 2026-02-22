package com.testingapp.testingapp.dto;

import lombok.Builder;

@Builder
public record EmployeeResponseDto(Long id, String firstName, String lastName, String email) {
}
