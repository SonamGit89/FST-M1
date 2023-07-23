from selenium.webdriver.common.by import By
from selenium import webdriver
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as expected_conditions

# Set up the Firefox Driver with WebDriverManger
service = FirefoxService(GeckoDriverManager().install())

# Start the Driver
with webdriver.Firefox(service=service) as driver:
    # Initialize the wait object
    wait = WebDriverWait(driver, 10)

    # Navigate to the URL
    driver.get("https://v1.training-support.net/selenium/tab-opener")
    # Print the title of the page
    print("Page title is: ", driver.title)
    # Store the current window/tab handle
    original_window = driver.current_window_handle
    # Print the handle of the current page
    print("Current window handle: ", driver.current_window_handle)
    # Find the link to open a new tab/window and click it
    # wait.until(EC.visibility_of_element_located((By.ID, "launcher")))
    driver.find_element(By.ID, "launcher").click()

    # Wait for new tab/window to open

    wait.until(expected_conditions.number_of_windows_to_be(2))
    # Print the handles of all the windows
    print("All window handles: ", driver.window_handles)

    # Switch focus to new tab/window
    driver.switch_to.window(driver.window_handles[1])
    wait.until(expected_conditions.title_is("Newtab"))
    # Print the handle of the currently active tab/window
    print("Current window handle: ", driver.current_window_handle)
    # Print the new tab/window title
    print("New Tab title: ", driver.title)
    # Print the new tab/window heading
    wait.until(expected_conditions.presence_of_element_located((By.XPATH, "//div[@class='content']")))
    heading = driver.find_element(By.XPATH, "//div[@class='content']")
    print(heading.text)

    # For the next tab/window
    # Find the link to open a new tab/window and click it
    driver.find_element(By.ID, "actionButton").click()
    # Wait for new tab/window to open
    wait.until(expected_conditions.number_of_windows_to_be(3))
    # Print the handles of all the windows
    print("All window handles: ", driver.window_handles)

    # Switch focus to new tab/window
    driver.switch_to.window(driver.window_handles[2])

    # Print the handle of the currently active tab/window
    print("Current window handle: ", driver.current_window_handle)
    # Print the new tab/window title
    print("New Tab title: ", driver.title)
    # Print the new tab/window heading
    wait.until(expected_conditions.presence_of_element_located((By.XPATH, "//div[@class='content']")))
    heading = driver.find_element(By.XPATH, "//div[@class='content']")
    print(heading.text)

    # close the browser
    driver.quit()
