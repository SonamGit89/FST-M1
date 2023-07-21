package session9;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Activity10 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NULL");

        // Open browser
        driver.get("https://v1.training-support.net/selenium/simple-form");
    }

    public static List<List<String>> readExcel(String filePath) {
        List<List<String>> data = new ArrayList<List<String>>();
        try {
            FileInputStream file = new FileInputStream(filePath);

            // Create Workbook instance holding reference to Excel file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through each rows one by one
            for (Row cells : sheet) {
                // Temp variable
                List<String> rowData = new ArrayList<String>();

                // For each row, iterate through all the columns
                for (Cell cell : cells) {
                    if (cell != null) {
                        // Store row data
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.add(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                String value = NumberToTextConverter.toText(cell.getNumericCellValue());
                                rowData.add(value);
                                break;
                        }
                    }
                }
                // Store row data in List
                data.add(rowData);
            }
            file.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name = "Registration")
    public static Object[][] signUpInfo() {
        String filePath = "src/test/resources/Sample.xlsx";
        List<List<String>> data = readExcel(filePath);
        return new Object[][]{
                {data.get(1)},
                {data.get(2)},
                {data.get(3)},
        };
    }

    @Test(dataProvider = "Registration")
    public void registerTest(List<String> rows) {
        // Find the input fields and enter text
        WebElement firstNameField = driver.findElement(By.xpath("//input[@id = 'firstName']"));
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id = 'lastName']"));
        WebElement emailField = driver.findElement(By.xpath("//input[@id = 'email']"));
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@id = 'number']"));

        // Clear the fields
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();

        // Enter the data in the fields
        firstNameField.sendKeys(rows.get(1));
        lastNameField.sendKeys(rows.get(2));
        emailField.sendKeys(rows.get(3));
        phoneNumberField.sendKeys(rows.get(4));

        // Click Submit
        driver.findElement(By.xpath("//input[contains(@class, 'ui green button')]")).click();

        // Switch to alert
        Alert message = driver.switchTo().alert();
        // Get alert message
        System.out.println("Alert message: " + message.getText());
        message.accept();

        // Refresh the page
        driver.navigate().refresh();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
