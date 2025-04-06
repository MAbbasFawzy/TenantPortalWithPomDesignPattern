package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SubmitRequestSubmitSubscription extends randomGenerator {

    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public SubmitRequestSubmitSubscription(WebDriver driver) {

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(serviceDropDownList).click();
        driver.findElement(serviceDropDownListRequest).click();

        Thread.sleep(4000);
        driver.findElement(serviceCategoryList).click();
        driver.findElement(serviceCategoryListOption).click();

        Thread.sleep(4000);
        driver.findElement(serviceCategories).click();
        driver.findElement(serviceCategoriesOption).click();


        driver.findElement(serviceDescription).sendKeys(requestSubscriptionDescription);

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
