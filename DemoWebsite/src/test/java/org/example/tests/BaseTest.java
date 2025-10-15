package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    // REPLACE THIS URL with the actual URL you are testing
    private static final String START_URL = "https://www.selenium.dev/selenium/web/web-form.html";

    @BeforeMethod
    public void setup() {
        // Use WebDriverManager to handle the ChromeDriver setup
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        // A common implicit wait that applies to all findElement calls
        // This is a global wait, but still not as reliable as an Explicit Wait for a specific element.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navigate to the web form page
        driver.get(START_URL);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}