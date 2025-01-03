package com.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Manually set the path to ChromeDriver (Ensure path is correct and exists)
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("http://localhost:8080/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the username, password fields, and submit button
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

            // Enter valid credentials
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            submitButton.click();

            // Validate successful login
            String expectedTitle = "Login successful!";  // Adjust this based on actual response
            WebElement responseElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            String actualResponse = responseElement.getText();

            assertEquals(expectedTitle, actualResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
