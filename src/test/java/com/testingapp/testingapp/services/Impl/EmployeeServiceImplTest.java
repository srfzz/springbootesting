package com.testingapp.testingapp.services.Impl;

import com.testingapp.testingapp.services.contracts.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers

class EmployeeServiceImplTest {
    @Autowired
private EmployeeService employeeService;
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine");
    @Test
    void testGetEmployeeByIdIsPresent_thenReturnEmployeeDto(){

        employeeService.findById(1l);
    }
}