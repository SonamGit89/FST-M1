import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class Activity6 {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);


        //Open browser
        driver.get("https://alchemy.hguy.co/jobs");
    }
    @Test
    public void jobSearchOption() {
        //navigate to the Jobs page
        WebElement jobsOption =  driver.findElement(By.xpath("//*[@id='menu-item-24']/a"));
        jobsOption.click();
        //Search for a particular job and wait for listings to show.
        WebElement searchText =driver.findElement(By.id("search_keywords"));
        //Enter Banks
        searchText.sendKeys("Banking", Keys.RETURN);
        //Click and open any one of the jobs listed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Search completed')]")));
        List<WebElement> listofjobs =driver.findElements(By.xpath("//div[@class='position']/h3"));
        System.out.println("no of Jobs found is  :" + listofjobs.size());
        for(WebElement e:listofjobs) {
            //Click the first job find in the list
            String jobText = e.getText().toLowerCase();
            if (jobText.contains("banking")) {

                actions.moveToElement(e);
                e.click();
            }
        }
            //Click the apply button
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='application_button button']"))).click();
            // print the email to the console.
            String emailId = driver.findElement(By.className("job_application_email")).getText();
            System.out.println("Email: " + emailId);
    }

    @AfterClass
    public void afterClass() {
        //Close browser
        driver.close();
    }
}

