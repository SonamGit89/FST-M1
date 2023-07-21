package project;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;

public class Activity2 {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.keep");
        options.setAppActivity(".activities.BrowseActivity");
        options.noReset();

        // Server Address
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Test method
    @Test
    public void addNewNote(){
        //Click the Create New Note button to add a new Note
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/new_note_button")));
        WebElement addButton = driver.findElement(AppiumBy.id("com.google.android.keep:id/new_note_button"));
        addButton.click();
        //Add a title for the note and add a small description.
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/editable_title")));
        WebElement title = driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title"));
        title.sendKeys("Test Note1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/edit_note_text")));
        WebElement desc = driver.findElement(AppiumBy.id("com.google.android.keep:id/edit_note_text"));
        desc.sendKeys("FST Appium Test");
        //Press the back button and make an assertion to ensure that the note was added.
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Navigate up")));
        WebElement backButton = driver.findElement(AppiumBy.accessibilityId("Navigate up"));
        backButton.click();
        String note = driver.findElement(AppiumBy.id("com.google.android.keep:id/index_note_title")).getText();
        Assert.assertEquals(note, "Test Note1");
    }
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}
