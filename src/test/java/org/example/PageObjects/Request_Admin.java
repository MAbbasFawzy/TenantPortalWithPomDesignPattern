package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class Request_Admin {

    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public Request_Admin(WebDriver driver) {
        this.driver = driver;
    }

    //Locators

    By txt_servicesPage = By.linkText("Services");

    By servicesSearchTextBox = By.xpath("//input[@placeholder='Find a service']");

    By service = By.xpath("//div[@class='grid place-items-center border bg-[var(--c1)] p-6 rounded-xl hover:shadow-lg h-32 cursor-pointer']");

    By requestButton = By.xpath("//button[@type='button']");

    By serviceDropDownList = By.xpath("//span[@aria-label='Select request type']");

    By serviceDropDownListRequest = By.xpath("//li[@aria-label='Request']");

    By serviceDropDownListSubscribe = By.xpath("//li[@aria-label='Subscribe']");

    By serviceDropDownListSubscribeOption = By.xpath("/html[1]/body[1]/div[5]/div[2]/ul[1]/li[1]");

    By serviceSubscribeCategoryList = By.xpath("//div[@class='p-dropdown p-component p-inputwrapper p-dropdown-clearable w-full h-10 flex items-center border-2']");

    By serviceSubscribeCategoryListOption = By.xpath("//div[contains(@class, 'p-dropdown')]//li[@class='p-dropdown-item' and @aria-label='200 MBPS']");

    By serviceSubscriptionDescription = By.xpath("//textarea[@class='p-inputtextarea p-inputtext p-component p-inputtextarea-resizable w-full rounded-2xl border-2 w-full rounded-2xl']");

    By serviceCategoryList = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]");

    By serviceCategoryListOption = By.xpath("//li[@aria-label='No Internet Connection']");

    By serviceCategories = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]");

    By serviceCategoriesOption = By.xpath("//li[@aria-label='IT Maintenance']");

    By serviceDescription = By.xpath("//textarea[@class='p-inputtextarea p-inputtext p-component p-inputtextarea-resizable w-full rounded-2xl border-2 w-full rounded-2xl']");

    By preferredVisitDateInput = By.xpath("//input[@class='vdatetime-input w-full my-wrapper-class rounded-3xl ps-3 border-2']");

    By submitButton = By.xpath("//button[@type='submit']");

    By txt_myRequestsPage = By.linkText("My Requests");

    By serviceCategoryListSubscribe = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]");

    String requestSubscriptionDescription = "Test!...." + visitor.numbers;


    //Action methods
    public void servicesPageOpenAndSearch() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(txt_servicesPage).click();
        driver.findElement(servicesSearchTextBox).click();
        driver.findElement(servicesSearchTextBox).sendKeys("Internet");
        driver.findElement(service).click();

    }

    public void openSubmitRequestForm() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        driver.findElement(requestButton).click();
    }

    public void selectServiceAndRequest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Random random = new Random();

        try {
            // Service selection flow
            wait.until(ExpectedConditions.elementToBeClickable(serviceDropDownList)).click();
            wait.until(ExpectedConditions.elementToBeClickable(serviceDropDownListRequest)).click();

            wait.until(ExpectedConditions.elementToBeClickable(serviceCategoryList)).click();
            wait.until(ExpectedConditions.elementToBeClickable(serviceCategoryListOption)).click();

            wait.until(ExpectedConditions.elementToBeClickable(serviceCategories)).click();
            wait.until(ExpectedConditions.elementToBeClickable(serviceCategoriesOption)).click();

            // Enter service description
            wait.until(ExpectedConditions.visibilityOfElementLocated(serviceDescription))
                    .sendKeys(requestSubscriptionDescription);

            // Date/Time selection
            WebElement preferredVisitDateInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@class='vdatetime-input w-full my-wrapper-class rounded-3xl ps-3 border-2']")));
            preferredVisitDateInput.click();

            Thread.sleep(500);
            // Random date selection
            selectRandomDate(wait, random);

            Thread.sleep(500);
            // Random time selection
            selectRandomTime(wait, random);

            Thread.sleep(500);

            // Scroll to the submit button using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

            // Submit form
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to select service and request: " + e.getMessage());
        }
    }


    private void selectRandomDate(WebDriverWait wait, Random random) {
        // Wait for date picker and select random day
        List<WebElement> enabledDays = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.vdatetime-calendar__month__day:not(.vdatetime-calendar__month__day--disabled)")));

        WebElement randomDay = enabledDays.get(random.nextInt(enabledDays.size()));
        wait.until(ExpectedConditions.elementToBeClickable(randomDay)).click();

        // Click Continue to move to time picker
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.vdatetime-popup__actions__button--confirm"))).click();
    }

    private void selectRandomTime(WebDriverWait wait, Random random) {
        // Select random hour (filter disabled)
        List<WebElement> enabledHours = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.vdatetime-time-picker__list--hours div:not(.vdatetime-time-picker__item--disabled)")));
        enabledHours.get(random.nextInt(enabledHours.size())).click();

        // Select random minute
        List<WebElement> minutes = driver.findElements(
                By.cssSelector("div.vdatetime-time-picker__list--minutes div"));
        minutes.get(random.nextInt(minutes.size())).click();

        // Select random AM/PM
        List<WebElement> suffixes = driver.findElements(
                By.cssSelector("div.vdatetime-time-picker__list--suffix div"));
        suffixes.get(random.nextInt(suffixes.size())).click();

        // Final confirmation
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.vdatetime-popup__actions__button--confirm"))).click();
    }

    public void assertRequest() throws InterruptedException {

        Thread.sleep(2000);
        driver.findElement(txt_myRequestsPage).click();

        driver.navigate().refresh();

        // Create an instance of WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Locate the first card element
        WebElement firstCardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='grid md:grid-cols-2 xl:grid-cols-3 gap-6 mb-20 pb-10']//a[1]")));

        // Extract the title
        String title = firstCardElement.findElement(By.xpath(".//h5[contains(@class, 'text-lg font-bold')]")).getText();

        // Extract the description
        String description = firstCardElement.findElement(By.xpath(".//p[contains(@class, 'mb-2')]")).getText();

        // Extract the service type
        String serviceType = firstCardElement.findElement(By.xpath(".//span[contains(text(), 'Type:')]")).getText();

        // Extract the start date
        String startDate = firstCardElement.findElement(By.xpath(".//span[contains(text(), 'Start Date:')]")).getText();

        // Extract the date and time
        String dateTime = firstCardElement.findElement(By.xpath(".//div[contains(@class, 'inline-flex flex-wrap gap-1')]")).getText();

        // Print the extracted data
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Start Date: " + startDate);
        System.out.println("Date and Time: " + dateTime);

        Assert.assertEquals(description, requestSubscriptionDescription);
    }

    public void selectServiceAndSubscribe() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(serviceDropDownList).click();
        driver.findElement(serviceDropDownListSubscribe).click();

        Thread.sleep(4000);
        driver.findElement(serviceCategoryListSubscribe).click();
        driver.findElement(serviceDropDownListSubscribeOption).click();

        Thread.sleep(4000);
        driver.findElement(serviceSubscribeCategoryList).click();
        driver.findElement(serviceSubscribeCategoryListOption).click();

        Thread.sleep(2000);
        driver.findElement(serviceSubscriptionDescription).sendKeys(requestSubscriptionDescription);

        // Create an instance of WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Click on the Preferred visit date input to open the date picker
        WebElement preferredVisitDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='vdatetime-input w-full my-wrapper-class rounded-3xl ps-3 border-2']")));
        preferredVisitDateInput.click();

        // Wait for the date picker to be visible
        WebElement datePicker = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'vdatetime-popup')]")));

        // Get today's date
        LocalDate today = LocalDate.now();

        // Select a future date (e.g., 10 days from today)
        LocalDate futureDate = today.plusDays(10); // Change the number of days as needed
        String futureDateString = futureDate.format(DateTimeFormatter.ofPattern("d")); // Get the day of the month

        Thread.sleep(2000);
        // Send the Enter key twice to confirm the selection
        preferredVisitDateInput.sendKeys(Keys.ENTER); // First Enter key press
        preferredVisitDateInput.sendKeys(Keys.ENTER); // Second Enter key press

        Thread.sleep(2000);
        driver.findElement(submitButton).click();
    }

    public void checkSubscriptionAdded() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        Thread.sleep(4000);

        driver.findElement(txt_myRequestsPage).click();

        driver.navigate().refresh();

        // Create an instance of WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Locate the first card element
        WebElement firstCardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='grid md:grid-cols-2 xl:grid-cols-3 gap-6 mb-20 pb-10']//a[1]")));

        // Extract the title
        String title = firstCardElement.findElement(By.xpath(".//h5[contains(@class, 'text-lg font-bold')]")).getText();

        // Extract the description
        String description = firstCardElement.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[4]/a[1]/p[2]")).getText();

        // Extract the date and time
        String dateTime = firstCardElement.findElement(By.xpath(".//div[contains(@class, 'inline-flex flex-wrap gap-1')]")).getText();

        // Extract the service type
        String serviceType = firstCardElement.findElement(By.xpath(".//span[contains(text(), 'Type:')]")).getText();

        // Extract the start date
        String startDate = firstCardElement.findElement(By.xpath(".//span[contains(text(), 'Start Date:')]")).getText();

        // Extract the duration
        String duration = firstCardElement.findElement(By.xpath(".//span[contains(text(), 'Duration:')]")).getText();

        // Extract the status
        String status = firstCardElement.findElement(By.xpath(".//span[contains(@class, 'text-sm')]")).getText();

        // Print the extracted data
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Date and Time: " + dateTime);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Start Date: " + startDate);
        System.out.println("Duration: " + duration);
        System.out.println("Status: " + status);

        Assert.assertEquals(description, requestSubscriptionDescription);
    }
}
