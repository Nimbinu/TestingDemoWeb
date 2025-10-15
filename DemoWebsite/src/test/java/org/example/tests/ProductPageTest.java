package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ProductPageTest extends BaseTest {

    @Test
    public void addProductToCartTest() {
        // --- 1. Login to the Site ---
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // --- 2. Verify successful login (Check for Inventory Page) ---
        wait.until(ExpectedConditions.urlContains("inventory"));

        // --- 3. Add a product to the cart (Sauce Labs Backpack) ---
        // Find the "Add to cart" button for a specific product using its ID
        String productAddToCartId = "add-to-cart-sauce-labs-backpack";
        WebElement addToCartBtn = driver.findElement(By.id(productAddToCartId));
        addToCartBtn.click();

        // --- 4. Verify the cart icon badge shows '1' ---
        WebElement cartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        String cartCount = cartBadge.getText();

        // Assertions (equivalent to the old success message check)
        Assert.assertEquals(cartCount, "1", "Product count in the cart is incorrect after adding an item.");

        System.out.println("Test Passed! Product added to cart successfully. Cart count: " + cartCount);
    }
}