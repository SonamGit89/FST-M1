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

public class Activity1 {
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
        options.setAppPackage("com.google.android.apps.tasks");
        options.setAppActivity(".ui.TaskListsActivity");
        options.noReset();

        // Server Address
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Test method
    @Test
    public void listAddTask1() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Create new task")));
        WebElement addNewTask = driver.findElement(AppiumBy.accessibilityId("Create new task"));
        addNewTask.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")));
        WebElement taskDesc = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title"));
        taskDesc.sendKeys("Complete Activity with Google Tasks");
        WebElement addTaskSave = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_done"));
        addTaskSave.click();
        // Find the result
        String result = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Complete Activity with Google Tasks']")).getText();
        // Assertion
        Assert.assertEquals(result, "Complete Activity with Google Tasks");
    }
    @Test
    public void listAddTask2() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Create new task")));
        WebElement addNewTask = driver.findElement(AppiumBy.accessibilityId("Create new task"));
        addNewTask.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")));
        WebElement taskDesc = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title"));
        taskDesc.sendKeys("Complete Activity with Google Keep");
        WebElement addTaskSave = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_done"));
        addTaskSave.click();
        String result1 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Complete Activity with Google Keep']")).getText();
        // Assertion
        Assert.assertEquals(result1, "Complete Activity with Google Keep");
    }
    @Test
    public void listAddTask3(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Create new task")));
        WebElement addNewTask = driver.findElement(AppiumBy.accessibilityId("Create new task"));
        addNewTask.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")));
        WebElement taskDesc = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title"));
        taskDesc.sendKeys("Complete the second Activity Google Keep");
        WebElement addTaskSave = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_done"));
        addTaskSave.click();
        String result2 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Complete the second Activity Google Keep']")).getText();
        // Assertion
        Assert.assertEquals(result2, "Complete the second Activity Google Keep");

    }

    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}
