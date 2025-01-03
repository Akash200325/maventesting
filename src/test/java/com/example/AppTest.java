package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AppTest {

    @Test
    void testGetGreeting() {
        App app = new App();
        String expected = App.GREETING_MESSAGE;
        String actual = app.getGreeting();
        assertEquals(expected, actual, "Greeting should be 'Hello World!'");
    }
    @Test
    void testWelcomeMessage() {
        App app = new App();
        String name = "Akash";
        String expected = "Hello, Akash!";
        String actual = app.welcomeMessage(name);
        assertEquals(expected, actual, "Welcome message should include the user's name.");
    }

    @Test
    void testWelcomeMessageWithEmptyName() {
        App app = new App();
        String name = "";
        String expected = "Hello, !";
        String actual = app.welcomeMessage(name);
        assertEquals(expected, actual, "Welcome message should handle empty names.");
    }

    @Test
    void testWelcomeMessageWithNullName() {
        App app = new App();
        String name = null;
        String expected = "Hello, null!";
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
