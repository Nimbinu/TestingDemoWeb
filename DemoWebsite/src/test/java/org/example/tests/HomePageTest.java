package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod; // Import BeforeMethod
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePageTest extends BaseTest {

    // Define the specific URL for this test
    private static final String WEB_FORM_URL = "https://www.selenium.dev/selenium/web/web-form.html";

    @BeforeMethod // Navigate to the correct page before the test runs
    public void navigateToHomePage() {
        driver.get(WEB_FORM_URL);
    }

    @Test
    public void verifyFormSubmissionWorkflow() {
        // ... (Your existing, correct test code) ...
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // --- Step 1: Verify Home Page Title (Initial Page) ---
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button")));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Web form";

        Assert.assertEquals(actualTitle, expectedTitle, "Page title verification failed!");
        System.out.println("Test Passed! Title is: " + actualTitle);

        // --- Step 2: Locate and Interact with the Text Input Field ---
        By myTextInputLocator = By.name("my-text");
        wait.until(ExpectedConditions.visibilityOfElementLocated(myTextInputLocator));

        driver.findElement(myTextInputLocator).sendKeys("Test Successful");

        // 2. Click the submit button
        driver.findElement(By.tagName("button")).click();

        // --- Step 3: Verify Success Message on the Next Page ---
        By successMessageLocator = By.id("message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));

        String successMessage = driver.findElement(successMessageLocator).getText();
        Assert.assertEquals(successMessage, "Received!", "Form submission failed.");
        System.out.println("Form Submission Test Passed! Success Message: " + successMessage);
    }
}