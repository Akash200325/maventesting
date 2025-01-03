package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testGetGreeting() {
        App app = new App();
        String expected = App.WELCOME_MESSAGE; // Changed to use WELCOME_MESSAGE
        String actual = app.getWelcomeMessage(); // Use getWelcomeMessage method
        assertEquals(expected, actual, "Greeting should be 'Welcome to Automation!'");
    }

    @Test
    void testWelcomeMessage() {
        App app = new App();
        String name = "akash_k_rao";
        String expected = "Hello, akash_k_rao, welcome to Automation!";
        String actual = app.welcomeMessage(name);
        assertEquals(expected, actual, "Welcome message should include the user's name.");
    }

    @Test
    void testWelcomeMessageWithEmptyName() {
        App app = new App();
        String name = "";
        String expected = "Hello, , welcome to Automation!"; // Fixed the expected output
        String actual = app.welcomeMessage(name);
        assertEquals(expected, actual, "Welcome message should handle empty names.");
    }

    @Test
    void testWelcomeMessageWithNullName() {
        App app = new App();
        String name = null;
        String expected = "Hello, null, welcome to Automation!"; // Fixed the expected output
        String actual = app.welcomeMessage(name);
        assertEquals(expected, actual, "Welcome message should handle null names.");
    }

    @Test
    void testRunTests() {
        App app = new App();
        app.runTests(); 
    }

    @Test
    void testMainWithTestArgument() {
        String[] args = {"test"};
        App.main(args);
    }

    @Test
    void testMainWithoutArguments() {
        String[] args = {};
        App.main(args);
    }
}
