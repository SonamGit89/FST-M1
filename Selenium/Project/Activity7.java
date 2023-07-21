import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Activity7 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        //Open browser
        driver.get("https://alchemy.hguy.co/jobs");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void jobListing() {
        //Locate the navigation menu and click the menu item that says “Post a Job”
        WebElement jobCreate = driver.findElement(By.xpath("//*[@id='menu-item-26']/a"));
        jobCreate.click();
        //Fill in the necessary details and click the button that says “Preview”.
        driver.findElement(By.id("create_account_email")).sendKeys("sonam111@gmail.com");
        driver.findElement(By.id("job_title")).sendKeys("Test Engineer");
        driver.findElement(By.id("job_location")).sendKeys("London");
        WebElement dropdown = driver.findElement(By.id("job_type"));
        Select singleSelect = new Select(dropdown);
        singleSelect.selectByVisibleText("Internship");
        driver.findElement(By.xpath("//iframe[@id='job_description_ifr']")).click();
        driver.findElement(By.xpath("//iframe[@id='job_description_ifr']")).sendKeys("Looking for a Job ");
        driver.findElement(By.id("application")).sendKeys("test@gmail.com");
        driver.findElement(By.id("company_name")).sendKeys("IBM");
        WebElement previewButton = driver.findElement(By.name("submit_job"));
        previewButton.click();
        //Click on the button that says “Submit Listing
        WebElement submitListing = driver.findElement(By.name("continue"));
        submitListing.click();
        WebElement jobsOption = driver.findElement(By.xpath("//*[@id='menu-item-24']/a"));
        jobsOption.click();
        //Verify that the job listing was posted by visiting the jobs page.
        WebElement searchText = driver.findElement(By.id("search_keywords"));
        searchText.sendKeys("Test Engineer", Keys.RETURN);
        WebElement jobPosted = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='position']/h3[1]"))));
        Assert.assertEquals(jobPosted.getText(), "Test Engineer");
    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();

    }
}


