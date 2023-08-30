package org.example;

import org.example.Greetings;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GreetingsTest
{
    private static final String DEFAULT_NAME = "John Doe";
    private static final String GREETINGS_MESSAGE = "Hello ";

    @Test
    public void testNullNameShouldPrintDefaultName()
    {
        Greetings objectUnderTest = new Greetings();
        String expectedString = GREETINGS_MESSAGE + DEFAULT_NAME;
        assertEquals(expectedString, objectUnderTest.sayHello(null));
    }

    @Test
    public void testShouldPrintCorrectGreeting()
    {
        Greetings objectUnderTest = new Greetings();
        String expectedString = GREETINGS_MESSAGE + "Bob";
        assertEquals(expectedString, objectUnderTest.sayHello("Bob"));
    }
}