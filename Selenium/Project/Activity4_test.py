from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/jobs")
    second_header_element = driver.find_element(By.XPATH, "//*[@id='post-16']/div/h2")
    second_header_name = second_header_element.text
    # Make sure it matches “Quia quis non” exactly.
    print("Second Header: ", second_header_name)

    # Close the browser
    driver.quit()