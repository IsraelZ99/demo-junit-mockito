package com.demo.demojunitmockito.controller;

import com.demo.demojunitmockito.dto.CalculatorDTO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {

    // Allows to inject into the underlying object the different (and relevant)
    @InjectMocks
    private CalculatorController calculatorController;

    static Integer num1;
    static Integer num2;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all");
        num1 = 10;
        num2 = 5;
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all");
        num1 = null;
        num2 = null;
    }

    @BeforeEach
    void init() {
        System.out.println("Before each");
    }

    @AfterEach
    void destroy() {
        System.out.println("After each");
    }

    @Test
    @DisplayName("Test Addition Success Scenario")
    void testAddFunction_Success() {
        Integer result = calculatorController.add(num1, num2);
        // Always perform an assertion
        assertEquals(15, result);
    }

    @Test
//    @Disabled
    @DisplayName("Test Addition Failure Scenario")
    void testAddFunction_Fail() {
        Integer result = calculatorController.add(num1 - 1, num2);
        // Deliberately give wrong expected is 15
        // Always perform an assertion
        Assertions.assertNotEquals(15, result);
    }

    @Test
    @DisplayName("Test Subtraction for num1 > num2 Success Scenario")
    void testSubFunctionNum1gtNum2() {
        Integer result = calculatorController.subtract(num1, num2);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Test Subtraction for num2 > num1 Success Scenario")
    void testSubFunctionNum2gtNum1() {
        Integer result = calculatorController.subtract(num1, num2 + 6);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Test multiplication")
    void testMultiply() {
        CalculatorDTO calculatorDTO = new CalculatorDTO();
        calculatorDTO.setNumber1(num1);
        calculatorDTO.setNumber2(num2);
        ResponseEntity<Integer> responseEntity = calculatorController.multiply(calculatorDTO);
        assertEquals(50, responseEntity.getBody());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue(),
                "Expecting te status as OK");
    }

}
