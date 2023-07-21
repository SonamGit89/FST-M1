import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Activity8 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        //Open browser
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    }

    @Test
    public void backendLogin() {
        //Find the username field of the login form and enter the username into that field.
        driver.findElement(By.id("user_login")).sendKeys("root");
        //Find the password field of the login form and enter the password into that field.
        driver.findElement(By.id("user_pass")).sendKeys(" pa$$w0rd");
        //Find the login button and click it.
        WebElement loginButton = driver.findElement(By.id("wp-submit"));
        loginButton.click();
        //Verify that you have logged in.
        String titleName = driver.getTitle();
        Assert.assertEquals("Dashboard ‹ Alchemy Jobs — WordPress", titleName);

    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
