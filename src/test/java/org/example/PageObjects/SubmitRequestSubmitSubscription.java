package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    By service = By.xpath("//div[@class='mb-5 flex items-center']");

    By requestButton = By.xpath("//button[@type='button']");

    By serviceDropDownList = By.xpath("//span[@class='p-dropdown-label p-inputtext p-dropdown-label-empty']");

    By serviceDropDownListRequest = By.xpath("//li[@aria-label='Request']");

    By serviceDropDownListSubscribe = By.xpath("//li[@aria-label='Subscribe']");

    By serviceDropDownListSubscribeOption = By.xpath("/html[1]/body[1]/div[5]/div[2]/ul[1]/li[1]");

    By serviceSubscribeCategoryList = By.xpath("//span[@class='p-dropdown-label p-inputtext p-dropdown-label-empty']");

    By serviceSubscribeCategoryListOption = By.xpath("//div[contains(@class, 'p-dropdown')]//li[@class='p-dropdown-item' and @aria-label='200 MBPS']");

    By serviceSubscriptionDescription = By.xpath("//textarea[@class='p-inputtextarea p-inputtext p-component p-inputtextarea-resizable w-full rounded-2xl border-2 w-full rounded-2xl']");

    By serviceSubscriptionList = By.xpath("//span[@class='p-dropdown-label p-inputtext p-dropdown-label-empty']");

    By serviceCategoryList = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]");

    By serviceCategoryListOption = By.xpath("//li[@aria-label='No Internet Connection']");

    By serviceCategories = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]");

    By serviceCategoriesOption = By.xpath("//li[@aria-label='IT Maintenance']");

    By serviceDescription = By.xpath("//textarea[@class='p-inputtextarea p-inputtext p-component p-inputtextarea-resizable w-full rounded-2xl border-2 w-full rounded-2xl']");

    By preferredVisitDateInput = By.xpath("//input[@class='vdatetime-input w-full my-wrapper-class rounded-3xl ps-3 border-2']");

    By submitButton = By.xpath("//button[@type='submit']");

    By submitSubscription = By.xpath("//button[@type='submit']");

    By txt_myRequestsPage = By.linkText("My Requests");

    By serviceCategoryListSubscribe = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]");

    By addButton = By.xpath("//button[@type='button']");

    By servicesTab = By.xpath("//body/div/div/div/main[@dir='ltr']/div/div/div/div/div/div/div/div/div/a[1]");

    By selectSubscribeButton = By.xpath("//body//div[@id='__nuxt']//div[@class='container mx-auto']//div//div//div[2]//div[1]//button[1]");

    String requestSubscriptionDescription = "Test!...." + visitor.numbers;


    //Action methods
    public void servicesPageOpenAndSearch() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(txt_servicesPage).click();
        driver.findElement(servicesSearchTextBox).click();
        driver.findElement(servicesSearchTextBox).sendKeys("Internet");
        driver.findElement(service).click();

    }

    public void servicesPageOpenAndSearcFromMyRequests() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(txt_myRequestsPage).click();
        Thread.sleep(500);
        driver.findElement(addButton).click();
        Thread.sleep(500);
        driver.findElement(servicesTab).click();
        Thread.sleep(500);
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
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            Thread.sleep(4000);
            // Step 1: Click the dropdown to open it using XPath
            WebElement dropdown = driver.findElement(By.xpath("//span[@class='p-dropdown-label p-inputtext p-dropdown-label-empty']"));
            dropdown.click();

            // Wait for the dropdown panel to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'p-dropdown-panel')]")
            ));

            Thread.sleep(4000);
            // Step 2: Select a specific service (e.g., "Router Installation")
            String serviceToSelect = "Router Installation";
            WebElement serviceOption = driver.findElement(
                    By.xpath("//li[@role='option']//span[contains(text(), '" + serviceToSelect + "')]")
            );
            Thread.sleep(500);
            serviceOption.click();

            Thread.sleep(500);
            // Enter service description
            wait.until(ExpectedConditions.visibilityOfElementLocated(serviceDescription))
                    .sendKeys(requestSubscriptionDescription);

            Thread.sleep(500);
            // Scroll within the pop-up dialog
            WebElement popupContent = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.p-dialog-content")));
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", popupContent);

            Thread.sleep(2000);

            // Date/Time selection
            WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='Select date']")));
            dateInput.click();

            /* Deleted the selection of hour, minute and am or pm and will make it work only with continue button
            // Wait for the date picker to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("vdatetime-popup")));

            Thread.sleep(500);
            // Select a random date
            List<WebElement> days = driver.findElements(By.xpath("//div[contains(@class, 'vdatetime-calendar__month__day') and not(contains(@class, 'vdatetime-calendar__month__day--disabled'))]"));
            if (days.isEmpty()) {
                throw new RuntimeException("No available days found in the date picker.");
            }
            int randomIndex = random.nextInt(days.size());
            WebElement selectedDay = days.get(randomIndex);
            selectedDay.click();

             */

            Thread.sleep(500);
            // Click the "Continue" button to proceed to the time picker
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'vdatetime-popup__actions__button--confirm')]")));
            continueButton.click();
            Thread.sleep(500);
            continueButton.click();

            /* Deleted the selection of hour, minute and am or pm and will make it work only with continue button
            // Wait for the time picker to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("vdatetime-time-picker")));



            Thread.sleep(500);
            // Select a random hour
            List<WebElement> hours = driver.findElements(By.xpath("//div[contains(@class, 'vdatetime-time-picker__list--hours')]/div[contains(@class, 'vdatetime-time-picker__item') and not(contains(@class, 'vdatetime-time-picker__item--disabled'))]"));
            if (hours.isEmpty()) {
                throw new RuntimeException("No available hours found in the time picker.");
            }
            int randomHourIndex = random.nextInt(hours.size());
            WebElement selectedHour = hours.get(randomHourIndex);
            selectedHour.click();

            Thread.sleep(500);
            // Select a random minute
            List<WebElement> minutes = driver.findElements(By.xpath("//div[contains(@class, 'vdatetime-time-picker__list--minutes')]/div[contains(@class, 'vdatetime-time-picker__item') and not(contains(@class, 'vdatetime-time-picker__item--disabled'))]"));
            if (minutes.isEmpty()) {
                throw new RuntimeException("No available minutes found in the time picker.");
            }
            int randomMinuteIndex = random.nextInt(minutes.size());
            WebElement selectedMinute = minutes.get(randomMinuteIndex);
            selectedMinute.click();

            Thread.sleep(500);
            // Select AM/PM
            List<WebElement> suffixes = driver.findElements(By.xpath("//div[contains(@class, 'vdatetime-time-picker__list--suffix')]/div[contains(@class, 'vdatetime-time-picker__item')]"));
            if (suffixes.isEmpty()) {
                throw new RuntimeException("No available AM/PM options found in the time picker.");
            }
            int randomSuffixIndex = random.nextInt(suffixes.size());
            WebElement selectedSuffix = suffixes.get(randomSuffixIndex);
            selectedSuffix.click();

             */

            Thread.sleep(500);
            // Scroll to the submit button and click it
            WebElement submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", submitButtonElement);
            Thread.sleep(500); // Optional: Add a small delay to allow the scroll to complete

            try {
                submitButtonElement.click();
            } catch (Exception e) {
                // Fallback to JavaScript click if regular click fails
                js.executeScript("arguments[0].click();", submitButtonElement);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to select service and request: " + e.getMessage());
        }
    }


    /* Component for date time picker that maybe used later

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

     */


    public void assertRequest() throws InterruptedException {


        driver.findElement(txt_myRequestsPage).click();

        Thread.sleep(4000);

        driver.navigate().refresh();

        // Create an instance of WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        /* Old way to get the data of the newly added request
        // Locate the first card element
        WebElement firstCardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/a[1]")));

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

         */
        WebElement card = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.card.flex.flex-col")));

        // Extract Service Title (e.g., "Internet Service #171")
        String serviceTitle = card.findElement(By.xpath(".//h5[@class='text-lg font-500 flex gap-1 mb-0']")).getText();
        System.out.println("Service Title: " + serviceTitle);

        // Extract Status (e.g., "Open")
        String status = card.findElement(By.xpath(".//span[contains(@class, 'capitalize min-w-20')]")).getText();
        System.out.println("Status: " + status);

        // Extract Service Type (e.g., "Request")
        String serviceType = card.findElement(By.xpath(".//span[contains(text(), 'Service Type:')]")).getText();
        System.out.println("Service Type: " + serviceType);

    }

    public void selectServiceAndSubscribe() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        driver.findElement(selectSubscribeButton).click();

        Random random = new Random();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(4000);
        driver.findElement(serviceSubscriptionList).click();

        /*
        driver.findElement(serviceDropDownListSubscribe).click();
         */

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

        // Date/Time selection
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Select date']")));
        dateInput.click();

        // Wait for the date picker to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("vdatetime-popup")));

        // Select a random date
        List<WebElement> days = driver.findElements(By.xpath("//div[contains(@class, 'vdatetime-calendar__month__day') and not(contains(@class, 'vdatetime-calendar__month__day--disabled'))]"));
        if (days.isEmpty()) {
            throw new RuntimeException("No available days found in the date picker.");
        }
        int randomIndex = random.nextInt(days.size());
        WebElement selectedDay = days.get(randomIndex);
        selectedDay.click();

        // Click the "Continue" button to proceed to the time picker
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'vdatetime-popup__actions__button--confirm')]")));
        continueButton.click();


        /*

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

         */

        Thread.sleep(2000);
        // Scroll to the submit button and click it
        WebElement submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(submitSubscription));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", submitButtonElement);
        Thread.sleep(500); // Optional: Add a small delay to allow the scroll to complete

        try {
            submitButtonElement.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if regular click fails
            js.executeScript("arguments[0].click();", submitButtonElement);
        }

    }

    public void checkSubscriptionAdded() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        Thread.sleep(4000);

        driver.findElement(txt_myRequestsPage).click();

        driver.navigate().refresh();

        // Create an instance of WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Locate the card (assuming it's the first one or you know how to locate it)
        WebElement card = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.card.flex.flex-col[href*='subscription/view?id=']")));

        // 1. Service Title: "Internet Service #240"
        String serviceTitle = card.findElement(By.xpath(".//h5[@class='text-lg font-500 flex gap-1 mb-0']")).getText();
        System.out.println("Service Title: " + serviceTitle);

        // 2. Status: "Open"
        String status = card.findElement(By.xpath(".//span[contains(@class, 'capitalize min-w-20')]")).getText();
        System.out.println("Status: " + status);

        // 3. Service Type: "Service Type: Subscription"
        String serviceType = card.findElement(By.xpath(".//span[contains(text(), 'Service Type:')]")).getText();
        System.out.println("Service Type: " + serviceType);

        // ✅ Assert that the service type is "Subscription"
        Assert.assertTrue(serviceType.contains("Subscription"), "Card is not of type 'Subscription'");

        // 4. Date and Time: e.g., "20/07/2025 08:40 am"
        String dateTime = card.findElement(By.xpath(".//div[@class='inline-flex flex-wrap gap-1']")).getText();
        System.out.println("Date and Time: " + dateTime);

        // 5. Request Type: "Internet Subscription - 12 Months"
        String subscriptionType = card.findElement(By.xpath(".//span[@class='text-sm font-400 mt-1']")).getText();
        System.out.println("Subscription Type: " + subscriptionType);

        // 6. Duration: "Annually"
        String duration = card.findElement(By.xpath(".//span[contains(text(), 'Duration:')]")).getText();
        System.out.println("Duration: " + duration);

        // 7. Type: "Type: 200 MBPS"
        String planType = card.findElement(By.xpath(".//span[contains(text(), 'Type:')]")).getText();
        System.out.println("Plan Type: " + planType);

        // 8. Description: "Test!....9012347890"
        String description = card.findElement(By.xpath(".//p[@class='text-[var(--c5)] text-sm font-300']")).getText();
        System.out.println("Description: " + description);

        // 9. Extract the link
        String cardLink = card.getAttribute("href");
        System.out.println("Card Link: " + cardLink);

        System.out.println("✅ Card verified and is of type 'Subscription'");

        /*

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

         */
    }
}
