package com.testingapp.testingapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;


@Builder
public record EmployeeRequestDto(
@NotBlank(message = "First name Should Not Be Empty")
@Pattern(regexp = "^[a_zA_Z\\s]+$",message = "First Name Will Contain only Letters and Spaces ")
        String firstName,
@NotBlank(message = "First name Should Not Be Empty")
@Pattern(regexp = "^[a_zA_Z\\s]+$",message = "Last Name Will Contain only Letters and Spaces ")
String lastName,
@Email
String email
) {
}
