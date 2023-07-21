from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/jobs")
    jobs_option_element = driver.find_element(By.XPATH, "//*[@id='menu-item-24']/a")
    jobs_option = jobs_option_element.click
    # Read the page title
    titleName = driver.title;

    # Verify that you are on the correct page
    if "Jobs" in titleName:
        print("Title contains 'Jobs'")
    else:
        print("Title does not contain 'Jobs'")

    # Close the browser
    driver.quit()
