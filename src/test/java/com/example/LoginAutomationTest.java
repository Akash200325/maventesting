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
        // Setting up the WebDriver for Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\chromedriver.exe"); // Correct path for ChromeDriver
        driver = new ChromeDriver(); 
    }

    @Test
    void testLogin() {
        try {
            // Navigating to the Sauce Demo login page
            driver.get("https://www.saucedemo.com");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            // Performing login using provided test credentials
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");
            loginButton.click();

            // Validating successful login by checking the page title or a unique element
            WebElement inventoryPageTitle = driver.findElement(By.className("title"));
            String expectedTitle = "PRODUCTS";
            String actualTitle = inventoryPageTitle.getText();

            assertEquals(expectedTitle.toLowerCase(), actualTitle.toLowerCase(), "Login test failed: Title mismatch.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Login test failed due to an exception.");
        }
    }

    @Test
    void testWelcomeMessage() {
        // Created an instance of App
        App app = new App();

        String result = app.welcomeMessage("AKASH");

        // Validating the result
        assertEquals("Hello, AKASH.");
    }

    @AfterAll
    static void tearDown() {
        // Closing the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
