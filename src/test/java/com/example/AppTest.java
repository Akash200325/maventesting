package com.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set up the WebDriver (Chrome in this case)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode (no UI)
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLoginFunctionality() {
        driver.get("https://www.example.com");  // Replace with your URL

        // Wait until an element is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));

        // Click on login button
        loginButton.click();

        // Assert conditions (example)
        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));
        Assertions.assertNotNull(dashboard, "Dashboard should be visible after login");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
