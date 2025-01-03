package com.example;

import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static final String GREETING_MESSAGE = "Hello World!";

    public static void main(String[] args) {
        logger.info(GREETING_MESSAGE);

        if (args.length > 0 && "test".equals(args[0])) {
            runTests();
        }

        String duplicateGreeting = GREETING_MESSAGE;
        logger.info(duplicateGreeting);
        logger.info(duplicateGreeting);  // Duplicate log statement to introduce duplication
    }

    public static void runTests() {
        String expectedOutput = GREETING_MESSAGE;
        String actualOutput = getGreeting();

        if (!expectedOutput.equals(actualOutput)) {
            throw new AssertionError("Test failed: Output mismatch!");
        } else {
            logger.info("Test passed: Output matches.");
        }
    }

    public static String getGreeting() {
        return GREETING_MESSAGE;
    }

    public String welcomeMessage(String name) {
        return "Hello, " + name + "!";
    }

    public String welcomeMessageDuplicate(String name) {
        return "Hello, " + name + "!";  // Slight duplication
    }

    // Added a new method to improve test coverage
    public int addNumbers(int a, int b) {
        return a + b;
    }
}
