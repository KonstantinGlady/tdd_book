package com.gik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        Calculator calculator = new Calculator();
        double result = calculator.add(3, 5);
        double expected = 8;
        assertEquals(expected, result,0);
    }
}