package com.testingapp.testingapp.repository;
import com.testingapp.testingapp.entity.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
//@Import(TestConfigurationContainer.class)
@DataJpaTest
//@ActiveProfiles("test")
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    private EmployeeEntity employeeEntity;


    @BeforeEach
    void setUp()
    {
        employeeEntity=EmployeeEntity.builder().id(1L).firstName("John").lastName("Doe").email("email@mail.com").build();
    }

    @Test
    @DisplayName("Email-valid")
    void testfindByEmail_whenEmailIsValid_thenReturnEmployee() {
        log.info("First we will insert data on the Employee Repository");
        //arrange
        employeeRepository.save(employeeEntity);
        log.info("testfindByEmail_whenEmailIsValid");
       // employeeRepository.findByEmail("");
        //act
        Optional<EmployeeEntity> employeeData=employeeRepository.findByEmail(employeeEntity.getEmail());
        assertTrue(employeeData.isPresent());
        assertEquals(employeeEntity.getEmail(),employeeData.get().getEmail());
        //assertThat(employeeData).hasValue(employeeEntity);
        //assert

    }
    @Test
    @DisplayName("Email-Invalid")
    void testfindByEmail_whenEmailIsNotValid_thenReturnNull() {
        log.info("testfindByEmail_whenEmailIsNotValid");
        Optional<EmployeeEntity> employeeData=employeeRepository.findByEmail("imsarfaraaj7@gmail.com");
       assertThat(employeeData).isEmpty();
       assertThat(employeeData).isNotNull();
    }
}