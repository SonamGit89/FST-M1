import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Activity5 {
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
    public void navigateJobs() {
        //Find the navigation bar and Select the menu item that says “Jobs” and click it.
        WebElement jobsOption =  driver.findElement(By.xpath("//*[@id='menu-item-24']/a"));
        jobsOption.click();
        //Read the page title
        String titleName = driver.getTitle();

        // Verify that you are on the correct page
        if (titleName.contains("Jobs")) {
            System.out.println("You are on the correct page.");
        } else {
            System.out.println("You are not on the correct page.");
        }

    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}
