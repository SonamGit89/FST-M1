from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager

service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/jobs")
    header_image = driver.find_element(By.CSS_SELECTOR, "#post-16 > header > div > img")
    src_attribute = header_image.get_attribute("src")

    # Print the attribute value
    print("image is: ", src_attribute)

    # Close the browser
    driver.quit()
