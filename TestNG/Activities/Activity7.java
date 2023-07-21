package session9;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import java.time.Duration;

public class Activity7 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        // Initialize Firefox driver object
        driver = new FirefoxDriver();
        // Initialize wait object
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Open browser
        driver.get("https://v1.training-support.net/selenium/login-form");
    }

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][] { { "admin", "password" }};
    }

    @Test (dataProvider = "Authentication")
    public void loginTestCase(String username, String password) {
        //Find username and pasword fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        //Enter values
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        //Click Log in
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        //Assert Message
        String loginMessage = driver.findElement(By.id("action-confirmation")).getText();
        Assert.assertEquals(loginMessage, "Welcome Back, admin");
    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }

    public static class Activity1 {
        // Declare the WebDriver object
        WebDriver driver;

        @BeforeMethod
        public void beforeMethod() {
            // Set up the Firefox driver
            WebDriverManager.firefoxdriver().setup();
            //Create a new instance of the Firefox driver
            driver = new FirefoxDriver();
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

            //Open browser
            driver.get("https://v1.training-support.net");
        }

        @Test
        public void exampleTestCase() {
            // Check the title of the page
            String title = driver.getTitle();

            //Print the title of the page
            System.out.println("Page title is: " + title);

            //Assertion for page title
            Assert.assertEquals("Training Support", title);

            //Find the clickable link on the page and click it
            driver.findElement(By.id("about-link")).click();

            //Print title of new page
            System.out.println("New page title is: " + driver.getTitle());

            Assert.assertEquals(driver.getTitle(), "About Training Support");
        }

        @AfterMethod
        public void afterMethod() {
            //Close the browser
            driver.quit();
        }

    }
}
