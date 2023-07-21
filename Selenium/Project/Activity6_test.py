from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait

service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Initialize the wait object
    wait = WebDriverWait(driver, 10)
    # Navigate to the URL
    driver.get("https://alchemy.hguy.co/jobs")
    jobs_option_element = driver.find_element(By.XPATH, "//*[@id='menu-item-24']/a")
    jobs_option_element.click()
    searchText = driver.find_element(By.ID, "search_keywords")
    # Enter Banks
    searchText.send_keys("Banking", Keys.RETURN)
    # Click and open any one of the jobs listed
    wait.until(EC.visibility_of_element_located((By.XPATH, "//span[contains(text(),'Search completed')]")))
    listofjobs = driver.find_elements(By.XPATH, "//div[@class='position']/h3")
    # Extract the text from each WebElement and convert them to strings
    job_titles = [job.text for job in listofjobs]
    # Now join the job titles into a single string
    job_titles_str = ", ".join(job_titles)
    # Print the list of job titles
    print("No of Jobs found is:", job_titles_str)

    for job in listofjobs:
        print(job.text)
    # Click the first job find in the list
    if len(listofjobs) > 0:
        # Click on the first job in the list
        listofjobs[0].click()

    # Click the apply button
    wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@class='application_button button']"))).click()
    # print the email to the console.
    email_id_element = driver.find_element(By.CLASS_NAME, "job_application_email")
    email = email_id_element.text
    print("Email: " + email);

    # Close the WebDriver
    driver.quit()