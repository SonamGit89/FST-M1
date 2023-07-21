import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Activity9 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Open browser
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    }

    @Test
    public void backendJoblist() {
        //Login into the page
        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys(" pa$$w0rd");
        WebElement loginButton = driver.findElement(By.id("wp-submit"));
        loginButton.click();
        //Locate the left hand menu and click the menu item that says “Job Listings”.
        WebElement jobListing = driver.findElement(By.xpath("//*[@id='menu-posts-job_listing']/a/div[3]"));
        jobListing.click();
        //Locate the “Add New” button and click it.
        WebElement addNew = driver.findElement(By.className("page-title-action"));
        addNew.click();
        WebElement crossClick = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/div/div/div[1]/button"));
        crossClick.click();
        //Fill in the necessary details.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='post-title-0']"))).sendKeys("Software Engineer");
        driver.findElement(By.id("_application")).sendKeys("sonam.dutta889@gmail.com");
        driver.findElement(By.id("_company_website")).sendKeys("www.ibm.com");
        driver.findElement(By.id("_company_twitter")).sendKeys("@IBM");
        driver.findElement(By.id("_job_location")).sendKeys("Kolkata");
        driver.findElement(By.id("_company_tagline")).sendKeys("Big Blue");
        WebElement positionField = driver.findElement(By.id("_filled"));
        positionField.click();
        WebElement featureListing = driver.findElement(By.id("_featured"));
        featureListing.click();
        driver.findElement(By.id("_job_expires")).sendKeys("July 20, 2023");
        WebElement publishButton1 = driver.findElement(By.xpath("//*[@id='editor']/div/div/div[1]/div/div[1]/div/div[2]/button[2]"));
        publishButton1.click();
        WebElement publishButton2 = driver.findElement(By.xpath("//*[@id='editor']/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[1]/div/button"));
        publishButton2.click();
        // Go to home page
        driver.findElement(By.xpath("//a[@class='components-button edit-post-fullscreen-mode-close has-icon']")).click();
        //click Job listing
        driver.findElement(By.xpath("//div[@class='wp-menu-name' and text()='Job Listings']")).click();
        //Assert the job posted
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='job_position']/a[1]")).getText(),"Software Engineer" );


    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();

    }
}
