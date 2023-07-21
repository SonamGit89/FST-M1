import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
public class Activity2 {
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
    public void readHeader() {
        //Get the heading of the webpage.
        String headerName = driver.findElement(By.className("entry-title")).getText();
        // Make sure it matches “Welcome to Alchemy Jobs” exactly.
        Assert.assertEquals("Welcome to Alchemy Jobs", headerName);

    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}

