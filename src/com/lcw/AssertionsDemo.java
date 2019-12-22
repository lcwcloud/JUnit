package com.lcw;

import org.junit.jupiter.api.Test;
import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class AssertionsDemo {
    private final Calculator calculator = new Calculator();
    private final Person person = new Person("name1", "name2");
    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2), "The optional failure message is now the last parameter");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- " + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together
        assertAll("person", () -> assertEquals("name1", person.getFirstName()), () -> assertEquals("name2", person.getLastName()));
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties", () -> {
            String firstName = person.getFirstName();
            assertNotNull(firstName);
            // Executed only if the previous assertion is valid.
            assertAll("first name", () -> assertTrue(firstName.startsWith("n")), () -> assertTrue(firstName.endsWith("e")));
        }, () -> {
            // Grouped assertion, so processed independently
            // of results of first name assertions.
            String lastName = person.getLastName();
            assertNotNull(lastName);

            // Executed only if the previous assertion is valid.
            assertAll("last name", () -> assertTrue(lastName.startsWith("n")), () -> assertTrue(lastName.endsWith("2")));
        });
    }
    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
        assertEquals(" / by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds
        assertTimeout(ofMinutes(2), () -> {

        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds , and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertions invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10ms by 91ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    private static String greeting() {
        return "Hello, World!";
    }
}
