import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Activity4 {
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
    public void readSecondHeader() {
        //Get the second heading on the page.
        String secondHeaderName = driver.findElement(By.xpath("//*[@id=\"post-16\"]/div/h2")).getText();
        //Make sure it matches “Quia quis non” exactly.
        Assert.assertEquals("Quia quis non", secondHeaderName);
    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}

