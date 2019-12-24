package com.lcw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

public class JREConditonsDemo {

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {}

    @Test
    @EnabledOnJre({JRE.JAVA_9, JRE.JAVA_10})
    void onJava9OrJava10() {}

    @Test
    @DisabledOnJre(JRE.JAVA_9)
    void notOnJava9() {}
}
