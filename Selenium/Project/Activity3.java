import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

public class Activity3 {
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
    public void HeaderImageURL() {
        //Get the url of the header image.
        WebElement headerImage = driver.findElement(By.cssSelector("#post-16 > header > div > img"));
        String imageURL = headerImage.getAttribute("src");
        //Print the url to the console.
        System.out.println("Header Image URL: " + imageURL);
    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}



