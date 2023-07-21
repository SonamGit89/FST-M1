from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/jobs")
    # headerName = driver.findElement(By.className("entry-title")).getText();
    header_element = driver.find_element(By.CLASS_NAME, "entry-title")
    header_text = header_element.text

    # Print the header text
    print("Header message: ", header_text)

    # Close the browser
    driver.quit()
