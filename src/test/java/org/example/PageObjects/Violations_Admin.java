package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

public class Violations_Admin {

    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public Violations_Admin(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By violationsAdmin = By.xpath("//span[normalize-space()='Violations']");

    By addNewViolation = By.xpath("//span[@class='ms-1']");

    By propertyList = By.xpath("//span[@aria-label='Property']");

    By searchPropertyList = By.xpath("//input[@role='searchbox']");

    By propertyListOption = By.xpath("/html[1]/body[1]/div[5]/div[2]/ul[1]/li[1]");

    By violationSubject = By.xpath("//input[@placeholder='Subject']");

    By violationsCategory = By.xpath("//span[@aria-label='Violation Category']");

    By searchViolationsCategory = By.xpath("//input[@role='searchbox']");

    By violationCategoryOption = By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[1]");

    By reportedBy = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/form[1]/div[1]/div[1]/div[1]/div[4]/div[1]/span[1]");

    By violationDate = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/form[1]/div[1]/div[1]/div[1]/div[5]/div[1]/span[1]/input[1]");

    By today = By.xpath("//button[@aria-label='Today']");

    By involvedPeople = By.xpath("//div[@class='p-multiselect-label p-placeholder']");

    By involvedSearchInput = By.xpath("/html[1]/body[1]/div[5]/div[1]/div[2]/input[1]");

    By involvedPeopleOption = By.xpath("");

    By violationDescription = By.xpath("//div[@aria-label='Editor editing area: main']");

    By createButton = By.xpath("//button[@type='submit']");

    By description = By.xpath("//div[@aria-label='Editor editing area: main']");

    String message = "Welcome to Yarn.%" + " " + visitor.numbers;

    // Action methods
    public void openAddNewViolation() {

        driver.findElement(violationsAdmin).click();

        driver.findElement(addNewViolation).click();

    }

    public void addViolationDetails() throws InterruptedException {

        // Set implicit wait globally (adjust timeout as needed)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        // Step 1: Click on the Property List dropdown
        driver.findElement(propertyList).click();

        // Step 2: Search for "Property 1" in the Property List search box
        driver.findElement(searchPropertyList).sendKeys("Property 1");

        // Step 3: Wait for and click the matching property option
        driver.findElement(propertyListOption).click();

        // Step 4: Fill in the Violation Subject
        driver.findElement(violationSubject).sendKeys("Test.");

        Thread.sleep(500);
        // Step 5: Click on the Violations Category dropdown
        driver.findElement(violationsCategory).click();

        // Step 6: Locate and click the desired violation category ("Parking Violation")
        String violationXPath = "//li[contains(@role, 'option') and contains(., '" + "Parking Violation" + "')]";
        WebElement violationOption = driver.findElement(By.xpath(violationXPath));
        violationOption.click();

        // Step 7: Click on the Reported By dropdown
        driver.findElement(reportedBy).click();

        // Step 8: Locate and click the desired reported by option ("Yarn Support")
        String reportedByXPath = "//li[contains(@role, 'option') and contains(., '" + "Yarn Support" + "')]";
        WebElement reportedByOption = driver.findElement(By.xpath(reportedByXPath));
        reportedByOption.click();

        Thread.sleep(1000);
        driver.findElement(violationDate).click();

        Thread.sleep(1000);
        driver.findElement(today).click();

        Thread.sleep(1000);
        driver.findElement(involvedPeople).click();

        Thread.sleep(1000);
        driver.findElement(involvedSearchInput).sendKeys("yarn.user.tenant");

        // Step 13: Locate and click the matching option from the dropdown
        String involvedOptionXPath = "//li[contains(@role, 'option') and contains(., '" + "yarn.user.tenant" + "')]";
        WebElement involvedOption = driver.findElement(By.xpath(involvedOptionXPath));
        involvedOption.click();

        Thread.sleep(1000);
        String violationDescription = message;
        driver.findElement(description).sendKeys(message);


        Thread.sleep(1000);
        // Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(createButton).click();

        Thread.sleep(1000);
        // Locate the parent <div> containing all the details
        WebElement cardBody = driver.findElement(By.cssSelector("div.card-body"));

        // Extract all child <div> elements
        List<WebElement> fields = cardBody.findElements(By.cssSelector("div.col-sm-4.flex.gap-4.items-center.border-bottom.py-3"));

        // Create a Map to store the extracted data
        Map<String, String> violationDetails = new HashMap<>();

        // Iterate through each field and extract the label and value
        for (WebElement field : fields) {
            // Extract the label (e.g., "Status:", "Violation Category:")
            String label = field.findElement(By.cssSelector("span.text-black")).getText().trim();

            // Extract the value (e.g., "Open", "Parking Violation")
            String value = field.findElement(By.xpath(".//span[not(contains(@class, 'text-black'))]")).getText().trim();

            // Store the label-value pair in the Map
            violationDetails.put(label, value);
        }

        // Print the extracted data
        for (Map.Entry<String, String> entry : violationDetails.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


    }

    public void checkViolationAddedInTenant() {

        // Maximize the browser window to ensure the Description column is visible
        driver.manage().window().maximize();

        // Locate all rows in the table
        List<WebElement> rows = driver.findElements(By.cssSelector("a.grid.grid-cols-6.sm\\:grid-cols-9.items-center.gap-8.pt-4"));

        // List to store descriptions
        List<String> descriptions = new ArrayList<>();

        // Iterate through each row and extract the Description column
        for (WebElement row : rows) {
            // Locate the Description column (third column in the grid)
            WebElement descriptionElement = row.findElement(By.cssSelector("div.col-span-3.hidden.sm\\:block"));
            String description = descriptionElement.getText().trim();

            // Add the description to the list
            descriptions.add(description);
        }

        // Print the extracted descriptions
        for (String description : descriptions) {
            System.out.println(description);
        }

        Assert.assertEquals(description, violationDescription);

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

}
