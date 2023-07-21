import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

public class Activity1 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        //Open browser
        driver.get("https://alchemy.hguy.co/jobs");
    }

    @Test
    public void readTitle() {
        //Get the title of the website.
        String titleName = driver.getTitle();
        //Make sure it matches “Alchemy Jobs – Job Board Application” exactly
        Assert.assertEquals("Alchemy Jobs – Job Board Application", titleName);

    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}