package com.example;

import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static final String GREETING_MESSAGE = "Hello World!";

    public static void main(String[] args) {
        logger.info(GREETING_MESSAGE);
        // If "test" argument passes, then we run the embedded test
        if (args.length > 0 && "test".equals(args[0])) {
            runTests();
        }

        // Adding more duplication in main method
        String duplicateGreeting = GREETING_MESSAGE;
        logger.info(duplicateGreeting);

        // Another duplicated block in main
        if (args.length > 0 && "test".equals(args[0])) {
            runTestsDuplicated();
        }

        // Additional duplicated code in main
        String anotherDuplicateGreeting = GREETING_MESSAGE;
        logger.info(anotherDuplicateGreeting);

        // Calling duplicated method
        runTestsDuplicatedAgain();
    }

    public static void runTests() {
        String expectedOutput = GREETING_MESSAGE;
        String actualOutput = getGreeting();
        // Here we have test assertion logic
        if (!expectedOutput.equals(actualOutput)) {
            throw new AssertionError("Test failed: Output mismatch!");
        } else {
            logger.info("Test passed: Output matches.");
        }

        // Duplicated test logic to simulate duplication
        String expectedOutputDup = GREETING_MESSAGE;
        String actualOutputDup = getGreeting();
        if (!expectedOutputDup.equals(actualOutputDup)) {
            throw new AssertionError("Test failed: Output mismatch (duplicated)!");
        } else {
            logger.info("Test passed: Output matches (duplicated).");
        }

        // Additional duplicated logic inside runTests
        String expectedOutputExtra = GREETING_MESSAGE;
        String actualOutputExtra = getGreeting();
        if (!expectedOutputExtra.equals(actualOutputExtra)) {
            throw new AssertionError("Test failed: Output mismatch (extra duplicated)!");
        } else {
            logger.info("Test passed: Output matches (extra duplicated).");
        }
    }

    public static void runTestsDuplicated() {
        String expectedOutput = GREETING_MESSAGE;
        String actualOutput = getGreeting();
        // Duplicated code block (exactly the same as runTests)
        if (!expectedOutput.equals(actualOutput)) {
            throw new AssertionError("Test failed: Output mismatch (duplicated method)!");
        } else {
            logger.info("Test passed: Output matches (duplicated method).");
        }

        // Another duplication
        String expectedOutputDup = GREETING_MESSAGE;
        String actualOutputDup = getGreeting();
        if (!expectedOutputDup.equals(actualOutputDup)) {
            throw new AssertionError("Test failed: Output mismatch (duplicated method)!");
        } else {
            logger.info("Test passed: Output matches (duplicated method).");
        }

        // Additional duplication for testing
        String expectedOutputAnotherDup = GREETING_MESSAGE;
        String actualOutputAnotherDup = getGreeting();
        if (!expectedOutputAnotherDup.equals(actualOutputAnotherDup)) {
            throw new AssertionError("Test failed: Output mismatch (additional duplication)!");
        } else {
            logger.info("Test passed: Output matches (additional duplication).");
        }
    }

    public static void runTestsDuplicatedAgain() {
        String expectedOutput = GREETING_MESSAGE;
        String actualOutput = getGreeting();
        // Duplicated code block (identical to previous methods)
        if (!expectedOutput.equals(actualOutput)) {
            throw new AssertionError("Test failed: Output mismatch (duplicated again)!");
        } else {
            logger.info("Test passed: Output matches (duplicated again).");
        }

        // Additional duplicated logic
        String expectedOutputDup = GREETING_MESSAGE;
        String actualOutputDup = getGreeting();
        if (!expectedOutputDup.equals(actualOutputDup)) {
            throw new AssertionError("Test failed: Output mismatch (duplicated again)!");
        } else {
            logger.info("Test passed: Output matches (duplicated again).");
        }

        // Even more duplication for testing
        String expectedOutputExtraDup = GREETING_MESSAGE;
        String actualOutputExtraDup = getGreeting();
        if (!expectedOutputExtraDup.equals(actualOutputExtraDup)) {
            throw new AssertionError("Test failed: Output mismatch (extra duplication)!");
        } else {
            logger.info("Test passed: Output matches (extra duplication).");
        }
    }

    // Method testing
    public static String getGreeting() {
        return GREETING_MESSAGE;
    }

    // Method tested in LoginAutomationTest.java
    public String welcomeMessage(String name) {
        return "Hello, " + name + "!";
    }

    // Duplicated method to simulate code duplication
    public String welcomeMessageDuplicated(String name) {
        return "Hello, " + name + "!"; // Same logic as welcomeMessage()
    }

    // Adding another duplicated method
    public String welcomeMessageDuplicatedAgain(String name) {
        return "Hello, " + name + "!"; // Same logic as welcomeMessage()
    }

    // Adding one more duplicated method
    public String welcomeMessageDuplicatedYetAgain(String name) {
        return "Hello, " + name + "!"; // Same logic as welcomeMessage()
    }
}
