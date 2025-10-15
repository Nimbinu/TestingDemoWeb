package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod; // Import BeforeMethod
import java.time.Duration;

public class ProductPageTest extends BaseTest {

    // Define the specific URL for this test (Swag Labs login)
    private static final String LOGIN_URL = "https://www.saucedemo.com/";

    @BeforeMethod // Navigate to the correct page before the test runs
    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
    }

    @Test
    public void addProductToCartTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // --- 1. Login to the Site ---
        // This wait will now succeed because navigateToLoginPage() runs first.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // --- 2. Verify successful login (Check for Inventory Page) ---
        wait.until(ExpectedConditions.urlContains("inventory"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list"))); // Wait for page content

        // --- 3. Add a product to the cart (Sauce Labs Backpack) ---
        String productAddToCartId = "add-to-cart-sauce-labs-backpack";
        WebElement addToCartBtn = driver.findElement(By.id(productAddToCartId));
        addToCartBtn.click();

        // --- 4. Verify the cart icon badge shows '1' ---
        By cartBadgeLocator = By.cssSelector(".shopping_cart_badge");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadgeLocator));

        WebElement cartBadge = driver.findElement(cartBadgeLocator);
        String cartCount = cartBadge.getText();

        Assert.assertEquals(cartCount, "1", "Product count in the cart is incorrect after adding an item.");
        System.out.println("Test Passed! Product added to cart successfully. Cart count: " + cartCount);
    }
}