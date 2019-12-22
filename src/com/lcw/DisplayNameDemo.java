package com.lcw;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A special test case")
public class DisplayNameDemo {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {}

    @Test
    @DisplayName("kl()87¶¶")
    void testWithDisplayNameContainingSpecialCharacters() {}

    @Test
    @DisplayName("  ")
    void testWithDisplayNameContainingEmoji() {}

}
