package com.example;

import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static final String WELCOME_MESSAGE = "Welcome to Automation!";

    public static void main(String[] args) {
        logger.info(WELCOME_MESSAGE);
        // If "test" argument is passed, then we run the embedded test
        if (args.length > 0 && "test".equals(args[0])) {
            runTests();
        }
    }

    public static void runTests() {
        String expectedOutput = WELCOME_MESSAGE;
        String actualOutput = getWelcomeMessage();
        // Test assertion logic
        if (!expectedOutput.equals(actualOutput)) {
            throw new AssertionError("Test failed: Output mismatch!");
        } else {
            logger.info("Test passed: Output matches.");
        }
    }

    // Existing method
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    // Add welcomeMessage(String name) to match the test
    public String welcomeMessage(String name) {
        return "Hello, " + name + ", welcome to Automation!";
    }

    // This method seems to be already tested in LoginAutomationTest.java
    public String personalizedWelcome(String name) {
        return "Hello, " + name + ", welcome to Automation!";
    }
}
