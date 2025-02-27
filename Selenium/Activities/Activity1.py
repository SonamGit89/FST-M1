from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

# Set up the Firefox Driver with WebDriverManger
service = FirefoxService(GeckoDriverManager().install())


# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Navigate to the URL
    driver.get("https://training-support.net/")

    # Get the title of the website
    website_title = driver.title
    print("Page title is: ", driver.title)

    # Find the "About Us" button on the page using ID and click it
    driver.find_element(By.ID, "about-link").click()

    # Print the title of the new page
    print("New page title is: ", driver.title)

    # close the browser
    driver.quit()