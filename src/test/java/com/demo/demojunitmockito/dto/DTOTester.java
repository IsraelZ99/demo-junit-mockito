package com.demo.demojunitmockito.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

public class DTOTester {

    @Test
    @DisplayName("Tests all DTO'S getter and setter")
    void testDTOs(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(CalculatorDTO.class);
        beanTester.testBean(PropertyDTO.class);
    }

}
