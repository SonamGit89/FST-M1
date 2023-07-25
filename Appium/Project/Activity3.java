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
public class Activity3 {
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
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        //Click the Create New Note button to add a new Note
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/new_note_button")));
        WebElement addButton = driver.findElement(AppiumBy.id("com.google.android.keep:id/new_note_button"));
        addButton.click();
        //Add a title for the note and add a small description.
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/editable_title")));
        WebElement title = driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title"));
        title.sendKeys("Test Note with Reminder");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/edit_note_text")));
        WebElement desc = driver.findElement(AppiumBy.id("com.google.android.keep:id/edit_note_text"));
        desc.sendKeys("FST mobile testing");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/menu_reminder")));
        driver.findElement(AppiumBy.id("com.google.android.keep:id/menu_reminder")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Pick a date & time']")));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Pick a date & time']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/time_spinner")));
        driver.findElement(AppiumBy.id("com.google.android.keep:id/time_spinner")).click();
        // Scroll to the "Afternoon" option in the dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Afternoon']")));
        String uiSelector = "new UiSelector().textContains(\"Afternoon\")";
        String scrollTo = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ")";
        driver.findElement(AppiumBy.androidUIAutomator(scrollTo)).click();
        //driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Afternoon']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.keep:id/save")));
        driver.findElement(AppiumBy.id("com.google.android.keep:id/save")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Navigate up")));
        WebElement backButton = driver.findElement(AppiumBy.accessibilityId("Navigate up"));
        backButton.click();
        String reminder_note = driver.findElement(AppiumBy.id("com.google.android.keep:id/reminder_chip_text")).getText();
        System.out.println("Reminder Time: " +reminder_note );
        Assert.assertEquals(reminder_note, "Today, 1:00 PM");
    }
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}






