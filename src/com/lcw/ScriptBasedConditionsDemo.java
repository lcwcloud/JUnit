package com.lcw;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScriptBasedConditionsDemo {
    @Test // static javascript expression
    @EnabledIf("2 * 3 == 6")
    void willBeExecuted() {
        // ...
        System.out.println("hello");
    }

    @RepeatedTest(10)
    @DisabledIf("Math.random() < 0.314159")
    void mightNotBeExecuted() {
        // ...
    }

    @Test // Regular expression testing bound system property.
    @DisabledIf("/32/.test(('systemProperty.get('os.arch')'))")
    void disabledOn32BitArchitectures() {
        assertFalse(System.getProperty("os.arch").contains("32"));
    }

    @Test
    @EnabledIf("'CI' == systemProperty.get('ENV')")
    void onlyOnCiServer() {
        assertTrue("CI".equals(System.getenv("ENV")));
    }

    @Test // Multi line script, custom engine name and custom reason.
    @EnabledIf(value = {
            "load('nashron:mozilla_compat.js')",
            "importPackage(java.time)",
            "",
            "var today = LocalDate.now()",
            "var tomorrow = today.plusDays(1)",
            "tomorrow.isAfter(today)"
        },
        engine = "nashorn",
        reason = "Self-fulfilling:{result}")
    void theDayAfterTomorrow() {
        LocalDate localDate = LocalDate.now();
        LocalDate tomorrow = localDate.plusDays(1);
        assertTrue(tomorrow.isAfter(localDate));
    }

}
