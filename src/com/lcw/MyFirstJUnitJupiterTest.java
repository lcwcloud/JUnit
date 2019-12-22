package com.lcw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyFirstJUnitJupiterTest {
    private final Calculator calculator = new Calculator();
    @Fast
    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }

    @FastTest
    void addFastTest() {}
}
