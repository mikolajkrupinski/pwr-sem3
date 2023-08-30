package org.example;

public class Greetings
{
    private static final String GREETING = "Hello ";
    public String sayHello(String arg)
    {
        return GREETING + (arg != null ? arg : "John Doe");
    }
}
