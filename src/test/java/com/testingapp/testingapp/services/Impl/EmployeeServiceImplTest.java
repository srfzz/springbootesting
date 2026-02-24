package com.testingapp.testingapp.services.Impl;

import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.entity.EmployeeEntity;
import com.testingapp.testingapp.exceptions.ResourceNotFoundException;
import com.testingapp.testingapp.mapper.EmployeeMapper;
import com.testingapp.testingapp.repository.EmployeeRepository;
import com.testingapp.testingapp.services.contracts.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@Testcontainers
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Spy
    EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17-alpine");
    @Test
    void testGetEmployeeByIdIsPresent_thenReturnEmployeeDto(){

        //asign
        Long id=1L;
        EmployeeEntity mockEployeeEntity=EmployeeEntity.builder()
                .id(id)
                .firstName("Sarfaraj")
                .lastName("Ansari")
                .email("sarfaraj77@gmail.com")
                .build();
        //stubb //assign
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEployeeEntity));
        //act

        EmployeeResponseDto employeeResponseDto=employeeServiceImpl.findById(id);

        //assert

        assertThat(employeeResponseDto.id().equals(id)).isTrue();
        assertThat(employeeResponseDto.firstName().equals(mockEployeeEntity.getFirstName())).isTrue();
        assertThat(employeeResponseDto.lastName().equals(mockEployeeEntity.getLastName())).isTrue();
        assertThat(employeeResponseDto.email().equals(mockEployeeEntity.getEmail())).isTrue();
        verify(employeeRepository,times(4)).findById(id);
        verify(employeeRepository,times(2)).save(null);
    }
}