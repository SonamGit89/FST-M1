from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait


service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Initialize the wait object
    wait = WebDriverWait(driver, 10)
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/jobs")
    jobCreate = driver.find_element(By.XPATH, "//*[@id='menu-item-26']/a")
    jobCreate.click()
    # Fill in the necessary details and click the button that says “Preview”.
    input_email = driver.find_element(By.ID, "create_account_email")
    input_email.send_keys("sonam111@gmail.com")
    input_job_title = driver.find_element(By.ID, "job_title")
    input_job_title.send_keys("Test Engineer")
    input_job_location = driver.find_element(By.ID, "job_location")
    input_job_location.send_keys("London")
    # Find the dropdown
    single_select = driver.find_element(By.ID, "job_type")
    # Pass the dropdown WebElement to the Select class and initialize the select object
    dropdown = Select(single_select)
    dropdown.select_by_visible_text("Internship")
    driver.find_element(By.XPATH, "//iframe[@id='job_description_ifr']").click()
    driver.find_element(By.XPATH, "//iframe[@id='job_description_ifr']").send_keys("Looking for a Job ")
    driver.find_element(By.ID, "application").send_keys("test@gmail.com")
    driver.find_element(By.ID, "company_name").send_keys("IBM")
    previewButton = driver.find_element(By.NAME, "submit_job")
    previewButton.click();
    # Click on the button that says “Submit Listing
    submitListing = driver.find_element(By.NAME, "continue")
    submitListing.click()
    jobsOption = driver.find_element(By.XPATH, "//*[@id='menu-item-24']/a")
    jobsOption.click();
    # Verify that the job listing was posted by visiting the jobs page.
    searchText = driver.find_element(By.ID, "search_keywords")
    searchText.send_keys("Test Engineer", Keys.RETURN)
    jobPosted = wait.until(EC.visibility_of_element_located((By.XPATH, "//div[@class='position']/h3[1]")))
    jobPosted.text
    print("New Job added is ", jobPosted.text)

    # Close the WebDriver
    driver.quit()
