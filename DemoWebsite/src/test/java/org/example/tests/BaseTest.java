package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass; // Use BeforeClass for driver setup
import org.testng.annotations.AfterClass; // Use AfterClass for driver teardown
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    // NOTE: We remove driver.get() from BaseTest.setup()

    @BeforeClass // Setup the driver before any test in the class runs
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        // Implicit wait applies globally after navigation
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass // Quit the driver after all tests in the class run
    public void teardownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}