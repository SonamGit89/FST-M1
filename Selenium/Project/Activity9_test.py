from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager
from selenium.webdriver.support.ui import WebDriverWait

service = FirefoxService(GeckoDriverManager().install())
# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Initialize the wait object
    wait = WebDriverWait(driver, 10)
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
    # Locate the left hand menu and click the menu item that says “Job Listings”.
    jobListing = driver.find_element(By.XPATH, "//*[@id='menu-posts-job_listing']/a/div[3]")
    jobListing.click()
    # Locate the “Add New” button and click it.
    addNew = driver.find_element(By.CLASS_NAME, "page-title-action")
    addNew.click()
    crossClick = driver.find_element(By.XPATH, "/html/body/div[6]/div/div/div/div/div/div/div/div[1]/button")
    crossClick.click()
    # Fill in the necessary details.
    post_title = wait.until(EC.visibility_of_element_located((By.XPATH, "//*[@id='post-title-0']")))
    post_title.send_keys("Software Engineer")
    application_email = driver.find_element(By.ID, "_application")
    application_email.send_keys("sonam.dutta889@gmail.com")
    company_website = driver.find_element(By.ID, "_company_website")
    company_website .send_keys("www.ibm.com")
    company_twitter = driver.find_element(By.ID, "_company_twitter")
    company_twitter.send_keys("@IBM")
    job_location = driver.find_element(By.ID, "_job_location")
    job_location.send_keys("Kolkata")
    tagline = driver.find_element(By.ID, "_company_tagline")
    tagline.send_keys("Big Blue")
    positionField = driver.find_element(By.ID, "_filled")
    positionField.click()
    featureListing = driver.find_element(By.ID, "_featured")
    featureListing.click()
    expire_date = driver.find_element(By.ID, "_job_expires")
    expire_date.send_keys("July 30, 2023")
    publishButton1 = driver.find_element(By.XPATH, "//*[@id='editor']/div/div/div[1]/div/div[1]/div/div[2]/button[2]")
    publishButton1.click()
    publishButton2 = driver.find_element(By.XPATH, "//*[@id='editor']/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[1]/div/button")
    publishButton2.click()
    # Go to home page
    driver.find_element(By.XPATH, "//a[@class='components-button edit-post-fullscreen-mode-close has-icon']").click()
    # click Job listing
    driver.find_element(By.XPATH, "//div[@class='wp-menu-name' and text()='Job Listings']").click()
    # Assert the job posted
    job_position = driver.find_element(By.XPATH, "//div[@class='job_position']/a[1]")
    job_position.text
    print("New job position is :", job_position.text)

    # Close the WebDriver
    driver.quit()
