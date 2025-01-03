package com.example.automation;

import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static final String GREETING_MESSAGE = "Hello World!";

    public static void main(String[] args) {
        logger.info(GREETING_MESSAGE);
        // If "test" argument passes,then we run the embedded test
        if (args.length > 0 && "test".equals(args[0])) {
            runTests();
        }
    }

    public static void runTests() {
        String expectedOutput = GREETING_MESSAGE;
        String actualOutput = getGreeting();
        //Here we have test assertion logic
        if (!expectedOutput.equals(actualOutput)) {
            throw new AssertionError("Test failed: Output mismatch!");
        } else {
            logger.info("Test passed: Output matches.");
        }
    }

    // Method testing
    public static String getGreeting() {
        return GREETING_MESSAGE;
    }

    //  Method tested in LoginAutomationTest.java
    public String welcomeMessage(String name) {
        return "Hello, " + name + "!";
    }
}
