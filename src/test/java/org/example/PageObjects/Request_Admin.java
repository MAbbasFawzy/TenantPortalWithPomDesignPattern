package org.example.PageObjects;

import com.sun.jdi.ThreadReference;
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
import java.util.concurrent.TimeUnit;


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

    By requestsAdminModule = By.xpath("//span[contains(text(),'Tenant Requests')]");

    By requestsSubModule = By.xpath("//a[@href='/request']");

    By usernameAdmin = By.xpath("//input[@placeholder='Username']");

    By passwordAdmin = By.xpath("//input[@placeholder='Password']");

    By loginButton = By.xpath("//button[@type='submit']");

    By allRequests = By.xpath("//span[normalize-space()='All Requests']");

    By search = By.xpath("//input[@id='RequestTable_searchbar']//input[@id='RequestTable_searchbar']");

    By idColumn = By.xpath("//th[2]//div[1]");

    By viewButton = By.xpath("//tbody/tr[1]/td[36]/a[1]");

    By assignedToDropDownList = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]");

    By assignedToSearch = By.xpath("//input[@role='searchbox']");

    By assignedToOption = By.xpath("//ul[@class='p-dropdown-items']//li[1]");

    By fromDate = By.xpath("//body/div[@id='__nuxt']/div[@id='wrapper']/div[@id='page-wrapper']/div[@class='page-container']/div[@class='wrapper wrapper-content animate__animated animate__fadeIn container']/div[@class='grid grid-cols-12 gap-y-4 gap-x-6']/div[@class='col-span-12 xl:col-span-4']/div[@class='card request-view-page h-full']/div[@class='card-body']/div[@class='row gap-y-4']/div[2]/div[1]/span[1]");

    By todayFromDate = By.xpath("/html[1]/body[1]/div[19]/div[1]/div[3]/button[1]");

    By toDate = By.xpath("//body/div[@id='__nuxt']/div[@id='wrapper']/div[@id='page-wrapper']/div[@class='page-container']/div[@class='wrapper wrapper-content animate__animated animate__fadeIn container']/div[@class='grid grid-cols-12 gap-y-4 gap-x-6']/div[@class='col-span-12 xl:col-span-4']/div[@class='card request-view-page h-full']/div[@class='card-body']/div[@class='row gap-y-4']/div[3]/div[1]/span[1]");

    By todayToDate = By.xpath("/html[1]/body[1]/div[19]/div[1]/div[3]/button[1]");

    By expectedHours = By.xpath("//input[@placeholder='Hours']");

    By submitStaffScheduleButton = By.xpath("//span[@class='p-button-label'][normalize-space()='Submit']");

    By startButton = By.xpath("//button[normalize-space()='Start']");

    By confirmStartButton = By.xpath("//button[@class='swal2-confirm swal2-styled swal2-default-outline']");

    By statusBadge = By.xpath("//body/div/main[@dir='ltr']/div/div/div/a[1]/div[1]/div[2]/span[1]");

    By statusMenu = By.xpath("//div[@class='dropdown print:hidden']//button[@id='dropdownMenuButton']");

    By pauseStatus = By.xpath("//button[normalize-space()='Pause']");

    By confirmPause = By.xpath("//button[@type='button'][normalize-space()='Pause']");

    By cancelRequest = By.xpath("//button[@class='dropdown-item text-center p-2'][normalize-space()='Cancel']");

    By confirmCancelRequest = By.xpath("//button[@type='button'][normalize-space()='Cancel']");

    By completeRequest = By.xpath("//button[normalize-space()='Complete']");

    By confirmCompleteRequest = By.xpath("//button[@type='button'][normalize-space()='Complete']");

    By subscriptionsModule = By.xpath("//span[normalize-space()='Subscriptions']");

    By subscriptionIDColumn = By.xpath("//th[2]//div[1]");

    By viewSubscriptionButton = By.xpath("//tbody/tr[1]/td[42]/a[1]");

    By startSubscriptionButton = By.xpath("//button[normalize-space()='Start']");

    By startDate = By.xpath("//body[1]/div[5]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]");

    By todayStartDate = By.xpath("//div[@class='e-footer-container']//button[@aria-label='Today']");

    By endDate = By.xpath("/html[1]/body[1]/div[19]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/span[1]");

    By todayEndDate = By.xpath("/html[1]/body[1]/div[20]/div[1]/div[3]/button[1]");

    By recurrence = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/span[1]");

    String optionToSelect = "Daily";

    By option = By.xpath("/html[1]/body[1]/div[20]/div[1]/ul[1]/li[1]");

    By activateButton = By.xpath("//span[normalize-space()='Activate']");

    By statusOfSubscription = By.xpath(".//span[contains(@class, 'badge')]");

    By statusList = By.xpath("//div[@class='dropdown']//button[@id='dropdownMenuButton']");

    By pauseSubStatus = By.xpath("//button[normalize-space()='Paused']");

    By pauseConfirm = By.xpath("//button[normalize-space()='Pause']");

    By okayPauseTenant = By.xpath("//button[normalize-space()='Ok']");

    By endSubStatus = By.xpath("//button[normalize-space()='Ended']");

    By confirmEndStatus = By.xpath("//button[normalize-space()='End']");

    By terminateStatus = By.xpath("//button[normalize-space()='Terminate']");

    By terminatedStatus = By.xpath("//button[normalize-space()='Terminated']");

    By cancelledSubStatus = By.xpath("//button[normalize-space()='Cancelled']");

    By cancelStatus = By.xpath("//button[@type='button'][normalize-space()='Cancel']");

    String requestSubscriptionDescription = "Test!...." + visitor.numbers;



    //Action methods

    public void setUsername(String username) {
        driver.findElement(usernameAdmin).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordAdmin).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

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

    public void openRequests() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        driver.findElement(requestsAdminModule).click();

        driver.findElement(requestsSubModule).click();

        driver.findElement(allRequests).click();

        Thread.sleep(2000);

    }

    public void searchRequestAndView() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Thread.sleep(500);
        driver.findElement(idColumn).click();

        Thread.sleep(500);
        driver.findElement(idColumn).click();

        Thread.sleep(500);
        driver.findElement(viewButton).click();

        Thread.sleep(500);
        driver.findElement(assignedToDropDownList).click();

        Thread.sleep(500);
        driver.findElement(assignedToSearch).sendKeys("IT manager");

        Thread.sleep(2000);
        driver.findElement(assignedToOption).click();

        Thread.sleep(500);
        driver.findElement(fromDate).click();

        Thread.sleep(500);
        driver.findElement(todayFromDate).click();

        Thread.sleep(500);
        driver.findElement(toDate).click();

        Thread.sleep(500);
        driver.findElement(todayToDate).click();

        driver.findElement(expectedHours).clear();
        driver.findElement(expectedHours).sendKeys("5");

        driver.findElement(submitStaffScheduleButton).click();

        Thread.sleep(2000);
        driver.findElement(startButton).click();

        driver.findElement(confirmStartButton).click();
    }

    public void checkStatusInProgress() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1: Locate the first card's status
        WebElement requestStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusBadge));

        // Step 2: Extract and print the status
        String status = requestStatus.getText();
        System.out.println("Status of the first card: " + status);

        Assert.assertEquals(status, "In Progress");

    }

    public void pauseRequest() {

        driver.findElement(statusMenu).click();

        driver.findElement(pauseStatus).click();

        driver.findElement(confirmPause).click();

        driver.findElement(okayPauseTenant).click();
    }

    public void checkPauseTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1: Locate the first card's status
        WebElement requestStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusBadge));

        // Step 2: Extract and print the status
        String status = requestStatus.getText();
        System.out.println("Status of the first card: " + status);

        Assert.assertEquals(status, "Paused");

    }

    public void cancelRequest() {

        driver.findElement(statusMenu).click();

        driver.findElement(cancelRequest).click();

        driver.findElement(confirmCancelRequest).click();
    }

    public void checkCancelledRequestTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1: Locate the first card's status
        WebElement requestStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusBadge));

        // Step 2: Extract and print the status
        String status = requestStatus.getText();
        System.out.println("Status of the first card: " + status);

        Assert.assertEquals(status, "Cancelled");


    }

    public void completeRequest() {

        driver.findElement(statusMenu).click();

        driver.findElement(completeRequest).click();

        driver.findElement(confirmCompleteRequest).click();

    }

    public void checkCompleteRequestTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1: Locate the first card's status
        WebElement requestStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusBadge));

        // Step 2: Extract and print the status
        String status = requestStatus.getText();
        System.out.println("Status of the first card: " + status);

        Assert.assertEquals(status, "Completed");

    }

    /*======================================================*/

    public void openSubscriptions() throws InterruptedException {


        Thread.sleep(1000);
        driver.findElement(requestsAdminModule).click();

        Thread.sleep(1000);
        driver.findElement(subscriptionsModule).click();

        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");

        Thread.sleep(3000);
        driver.findElement(subscriptionIDColumn).click();

        Thread.sleep(3000);
        driver.findElement(subscriptionIDColumn).click();

        Thread.sleep(1000);
        driver.findElement(viewSubscriptionButton).click();

    }

    public void startSubscription() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(startButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 3: Wait for the pop-up dialog to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog-header"))); // Ensure the pop-up dialog is visible

        // Step 4: Generate dynamic start and end dates
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.plusDays(1); // Start date: tomorrow
        LocalDate endDate = today.plusMonths(6); // End date: 6 months from today

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);

        // Step 5: Interact with the Start Date field
        WebElement startDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".e-datepicker.e-input")));
        startDateInput.clear();
        startDateInput.sendKeys(formattedStartDate); // Use the dynamically generated start date
        startDateInput.sendKeys("\n"); // Simulate pressing Enter

        // Step 6: Interact with the End Date field
        WebElement endDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class='e-control e-datepicker e-lib e-input e-keyboard'])[2]")));
        endDateInput.clear();
        endDateInput.sendKeys(formattedEndDate); // Use the dynamically generated end date
        endDateInput.sendKeys("\n"); // Simulate pressing Enter

        // Step 7: Select the Billing Recurrence from the dropdown
        WebElement billingRecurrenceDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[19]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]")));
        billingRecurrenceDropdown.click();

        // Wait for the dropdown options to load and select an option
        WebElement billingRecurrenceOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[20]/div[1]/ul[1]/li[3]"))); // Replace with the actual text
        billingRecurrenceOption.click();

        /*
        Thread.sleep(3000);
        driver.findElement(startDate).click();

        Thread.sleep(3000);
        driver.findElement(todayStartDate).click();

        Thread.sleep(3000);
        driver.findElement(endDate).click();

        Thread.sleep(3000);
        driver.findElement(todayEndDate).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement billingRecurrenceDropdown = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]")));
        billingRecurrenceDropdown.click();

        WebElement billingRecurrenceOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//li[@role='option' and @aria-label='Monthly']"))); // Replace with your desired option

        billingRecurrenceOption.click();

         */
        driver.findElement(activateButton).click();

    }

    public void checkActiveTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            // Step 1: Wait for the grid container to load
            By gridContainer = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[4]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(gridContainer));

            // Step 2: Locate the first card's status
            By statusOfSubscription = By.xpath("//body/div/main[@dir='ltr']/div/div/div/a[1]/div[1]/div[2]/span[1]");
            WebElement subscriptionStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusOfSubscription));

            // Step 3: Scroll the element into view (optional, if needed)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionStatus);

            // Step 4: Extract and print the status
            String status = subscriptionStatus.getText();
            System.out.println("Status of the first card: " + status);

            // Step 5: Assert the status
            Assert.assertEquals(status, "Active");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking the active tenant.");
        }

    }

    public void pauseSubsriptionAdmin() throws InterruptedException {

        Thread.sleep(500);
        driver.findElement(statusList).click();

        Thread.sleep(500);
        driver.findElement(pauseSubStatus).click();

        Thread.sleep(500);
        driver.findElement(pauseConfirm).click();

        driver.navigate().refresh();

        Thread.sleep(2000);

    }

    public void checkPauseSubTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            // Step 1: Wait for the grid container to load
            By gridContainer = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[4]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(gridContainer));

            // Step 2: Locate the first card's status
            By statusOfSubscription = By.xpath("//body/div/main[@dir='ltr']/div/div/div/a[1]/div[1]/div[2]/span[1]");
            WebElement subscriptionStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusOfSubscription));

            // Step 3: Scroll the element into view (optional, if needed)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionStatus);

            // Step 4: Extract and print the status
            String status = subscriptionStatus.getText();
            System.out.println("Status of the first card: " + status);

            // Step 5: Assert the status
            Assert.assertEquals(status, "Paused");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking the active tenant.");
        }

    }

    public void endSubsriptionAdmin() throws InterruptedException {

        driver.findElement(statusList).click();

        driver.findElement(endSubStatus).click();

        driver.findElement(confirmEndStatus).click();

        Thread.sleep(2000);

        driver.navigate().refresh();

        Thread.sleep(2000);

    }


    public void checkEndSubTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            // Step 1: Wait for the grid container to load
            By gridContainer = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[4]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(gridContainer));

            // Step 2: Locate the first card's status
            By statusOfSubscription = By.xpath("//body/div/main[@dir='ltr']/div/div/div/a[1]/div[1]/div[2]/span[1]");
            WebElement subscriptionStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusOfSubscription));

            // Step 3: Scroll the element into view (optional, if needed)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionStatus);

            // Step 4: Extract and print the status
            String status = subscriptionStatus.getText();
            System.out.println("Status of the first card: " + status);

            // Step 5: Assert the status
            Assert.assertEquals(status, "Ended");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking the active tenant.");
        }

    }

    public void terminateSubscriptionAdmin() throws InterruptedException {

        driver.findElement(statusList).click();

        driver.findElement(terminatedStatus).click();

        driver.findElement(terminateStatus).click();

        Thread.sleep(2000);

        driver.navigate().refresh();

        Thread.sleep(2000);

    }

    public void checkTerminatedSubTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            // Step 1: Wait for the grid container to load
            By gridContainer = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[4]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(gridContainer));

            // Step 2: Locate the first card's status
            By statusOfSubscription = By.xpath("//body/div/main[@dir='ltr']/div/div/div/a[1]/div[1]/div[2]/span[1]");
            WebElement subscriptionStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusOfSubscription));

            // Step 3: Scroll the element into view (optional, if needed)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionStatus);

            // Step 4: Extract and print the status
            String status = subscriptionStatus.getText();
            System.out.println("Status of the first card: " + status);

            // Step 5: Assert the status
            Assert.assertEquals(status, "Terminated");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking the active tenant.");
        }

    }

    public void cancelledSubscriptionAdmin() throws InterruptedException {

        driver.findElement(statusList).click();

        driver.findElement(cancelledSubStatus).click();

        driver.findElement(cancelStatus).click();

        Thread.sleep(2000);

        driver.navigate().refresh();

        Thread.sleep(2000);


    }

    public void checkCancelledSubTenant() {

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            // Step 1: Wait for the grid container to load
            By gridContainer = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[4]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(gridContainer));

            // Step 2: Locate the first card's status
            By statusOfSubscription = By.xpath("//body/div/main[@dir='ltr']/div/div/div/a[1]/div[1]/div[2]/span[1]");
            WebElement subscriptionStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(statusOfSubscription));

            // Step 3: Scroll the element into view (optional, if needed)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionStatus);

            // Step 4: Extract and print the status
            String status = subscriptionStatus.getText();
            System.out.println("Status of the first card: " + status);

            // Step 5: Assert the status
            Assert.assertEquals(status, "Cancelled");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking the active tenant.");
        }

    }

}
