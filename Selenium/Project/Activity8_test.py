from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager


service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/jobs/wp-admin")
    user_input = driver.find_element(By.ID, "user_login")
    user_input.send_keys("root")
    # Find the password field of the login form and enter the password into that field.
    password_input = driver.find_element(By.ID, "user_pass")
    password_input.send_keys("pa$$w0rd")
    # Find the login button and click it.
    loginButton = driver.find_element(By.ID, "wp-submit")
    loginButton.click()
    # Verify that you have logged in.
    titleName = driver.title
    print("Dashboard ‹ Alchemy Jobs — WordPress", titleName)

    # Close the WebDriver
    driver.quit()
