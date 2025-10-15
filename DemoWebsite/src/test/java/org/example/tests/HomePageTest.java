package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePageTest extends BaseTest {

    // Best Practice: Use one test method for one complete, related workflow
    // This method combines title verification and form submission.
    @Test
    public void verifyFormSubmissionWorkflow() {

        // Initialize WebDriverWait once for all explicit waits in this test
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // --- Step 1: Verify Home Page Title (Initial Page) ---

        // Wait for a known element on the page (e.g., the submit button) to ensure the page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button")));

        String actualTitle = driver.getTitle();
        String expectedTitle = "Web form"; // Assuming this is the title of the starting page

        Assert.assertEquals(actualTitle, expectedTitle, "Page title verification failed!");
        System.out.println("Test Passed! Title is: " + actualTitle);

        // --- Step 2: Locate and Interact with the Text Input Field ---

        // The Fix: Explicitly wait for the text input field to be visible
        // before attempting to interact with it.
        By myTextInputLocator = By.name("my-text");
        wait.until(ExpectedConditions.visibilityOfElementLocated(myTextInputLocator));

        // 1. Locate the text input field using its name and send keys
        driver.findElement(myTextInputLocator).sendKeys("Test Successful");

        // 2. Click the submit button
        driver.findElement(By.tagName("button")).click();

        // --- Step 3: Verify Success Message on the Next Page ---

        // 3. Verify success message (the text "Received!") is visible on the next page
        By successMessageLocator = By.id("message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));

        String successMessage = driver.findElement(successMessageLocator).getText();
        Assert.assertEquals(successMessage, "Received!", "Form submission failed.");
        System.out.println("Form Submission Test Passed! Success Message: " + successMessage);
    }

    // NOTE: I've commented out your original individual test methods
    // because when tests run independently (without dependencies) and
    // perform steps on the same page, the second test often fails
    // if it needs to re-navigate or if the browser state is wrong.
    // The combined method above is more reliable for a single workflow.

    /* @Test
    public void verifyHomePageTitle() {
        // ... (Your original title verification code) ...
    }

    @Test
    public void verifyTextInput() {
        // ... (Your original text input code - which was failing) ...
    }
    */
}