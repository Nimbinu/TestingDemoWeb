package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration; // Make sure this is imported

public class ProductPageTest extends BaseTest {

    @Test
    public void addMacBookToCartTest() {
        // Use Explicit Wait for the search box to be ready after page load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));

        // Search for MacBook
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("MacBook");
        searchBox.submit();

        // Click on MacBook link
        WebElement macBookLink = driver.findElement(By.linkText("MacBook"));
        macBookLink.click();

        // Wait for the Add to Cart button to be clickable on the product page
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-cart")));
        addToCartBtn.click();

        // Verify success alert using Explicit Wait
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));

        // Check the success message text
        Assert.assertTrue(successMsg.getText().contains("Success"), "Product added to cart verification failed!");

        System.out.println(successMsg.getText());
    }
}

