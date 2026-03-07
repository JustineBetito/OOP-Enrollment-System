package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {


    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Test
    void shouldAddTwoNumbers() {


        // tinaggal na yung arrange

        //Act
        calculator.sum(10, 5);

        //Assert
        assertEquals(15, calculator.getAnswer());
    }

    @Test
    void shouldSubtractTwoNumber() {
        // tinaggal na yung arrange

        //Act
        calculator.subtract(10, 2);

        //Assert
        assertEquals(8, calculator.getAnswer());
    }

    @Test
    void shouldMultiplyTwoNumber() {
        // tinaggal na yung arrange

        //Act
        calculator.multiply(5, 5);

        //Assert
        assertEquals(25, calculator.getAnswer());
    }

    @Test
    void shouldDivideTwoNumber(){
        // tinaggal na yung arrange

        //Act
        calculator.divide(12, 2);

        //Assert
        assertEquals(6, calculator.getAnswer());
    }

}