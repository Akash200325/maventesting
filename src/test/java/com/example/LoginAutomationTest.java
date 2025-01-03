package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginAutomationTest {

    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        // Set up WebDriver for Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    void testLogin() {
        try {
            // Navigating to a sample login page
            driver.get("https://www.example.com/login");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Performing login with test credentials
            usernameField.sendKeys("test_user");
            passwordField.sendKeys("password123");
            loginButton.click();

            // Validating successful login by checking the page title or a unique element
            WebElement dashboardTitle = driver.findElement(By.className("dashboard-title"));
            String expectedTitle = "Dashboard";
            String actualTitle = dashboardTitle.getText();

            assertEquals(expectedTitle.toLowerCase(), actualTitle.toLowerCase(), "Login test failed: Title mismatch.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Login test failed due to an exception.");
        }
    }

    @Test
    void testWelcomeMessage() {
        // Create an instance of the App class
        App app = new App();

        // Testing personalized welcome message with a name
        String result = app.welcomeMessage("Alice");

        // Validating the result
        assertEquals("Hello, Alice, welcome to Automation!", result, "The welcome message should be correct.");
    }

    @Test
    void testLoginWithInvalidCredentials() {
        try {
            // Navigating to the login page
            driver.get("https://www.example.com/login");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Attempt login with invalid credentials
            usernameField.sendKeys("invalid_user");
            passwordField.sendKeys("wrong_password");
            loginButton.click();

            // Validating failure of login by checking for error message
            WebElement errorMessage = driver.findElement(By.id("errorMessage"));
            String expectedErrorMessage = "Invalid username or password.";
            String actualErrorMessage = errorMessage.getText();

            assertEquals(expectedErrorMessage, actualErrorMessage, "Login test failed: Error message mismatch.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Login test failed due to an exception.");
        }
    }

    @AfterAll
    static void tearDown() {
        // Closing the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
