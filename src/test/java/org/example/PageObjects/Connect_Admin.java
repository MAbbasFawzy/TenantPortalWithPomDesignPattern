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
import java.util.List;

public class Connect_Admin extends randomGenerator {

    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public Connect_Admin(WebDriver driver) {
        this.driver = driver;
    }

    //Locators

    By messagesAdmin = By.xpath("//span[normalize-space()='Messages']");

    By newMessageButton = By.xpath("//a[@class='btn btn-block btn-primary']");

    By recipientList = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]");

    By searchRecipientList = By.xpath("//input[@role='searchbox']");

    By recipientListOption = By.xpath("//li[contains(@class, 'p-multiselect-item') and contains(., 'yarn.user.tenant')]");

    By subject = By.xpath("//input[@id='subject']");

    By topic = By.xpath("//div[@aria-label='Editor editing area: main']");

    By submitMessage = By.xpath("//button[@type='submit']");

    By messagesTenant = By.xpath("//a[@href='/tenant/message']");

    By broadcastAdmin = By.xpath("//a[@href='/broadcast']");

    By newBroadcastButton = By.xpath("//a[normalize-space()='New Broadcast']");

    By propertyListBroadcast = By.xpath("//div[@class='p-multiselect p-component p-inputwrapper p-multiselect-chip w-full h-10 flex items-center property-dropdown w-fit']");

    By broadcastPropertySearch = By.xpath("//input[@role='searchbox']");

    By broadcastRecipientPropertyOption = By.xpath("//ul[@role='listbox']//li[.//span[text()='Property 1']]");

    By recipientListBroadcast = By.xpath("/html/body/div[2]/div/div[1]/div[2]/div[3]/form/div/div[1]/div[2]/div[2]/div/div/div[2]");

    By searchRecipientListBroadcast = By.xpath("//input[@role='searchbox']");

    By broadcastRecipientOption = By.xpath("/html[1]/body[1]/div[4]/div[2]/ul[1]/li[1]");

    By closeIcon = By.xpath("//button[@aria-label='Close']");

    By checkbox = By.xpath("/html/body/div[4]/div[1]/div[1]/input");

    By closeIconRecipientList  = By.xpath("//button[@aria-label='Close']");

    By broadcastSubject = By.xpath("//input[@id='subject']");

    By broadcastTopic = By.xpath("//div[@aria-label='Editor editing area: main']");

    By attachFiles = By.xpath("//div[@class='p-fileupload-content']");

    String message = "Welcome to Yarn.%" + " " + visitor.numbers;

    //Action methods

    public void openMessagesFromAdmin() {

        driver.findElement(messagesAdmin).click();
        driver.findElement(newMessageButton).click();

    }

    public void fillInMessageDetails(String username) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        Thread.sleep(500);
        driver.findElement(recipientList).click();

        driver.findElement(searchRecipientList).sendKeys(username);

        Thread.sleep(500);
        driver.findElement(recipientListOption).click();

        driver.findElement(subject).sendKeys("Welcome to Yarn.");

        driver.findElement(topic).sendKeys(message);
        System.out.println(message);

        // Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(submitMessage).click();
        System.out.println("Test");

    }

    public void openMessagesTenant() throws InterruptedException {

        // Click on the messages tenant element
        driver.findElement(messagesTenant).click();
        Thread.sleep(4000);

        // Locate the first row (most recently added row)
        List<WebElement> rows = driver.findElements(By.cssSelector(".grid > div"));

        if (!rows.isEmpty()) {
            // Click on the first row's clickable element (e.g., checkbox or link)
            WebElement firstRow = rows.get(0); // Get the first row

            // Alternatively, click on a link or button in the first row
            WebElement linkInFirstRow = firstRow.findElement(By.tagName("a"));
            linkInFirstRow.click();
        }

        Thread.sleep(4000);
        // Wait for the message container to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.flex.justify-between.gap-4")));

        // Step 4: Extract Message Content
        String messageContent = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]/p[1]"))
                .getText();
        System.out.println("Message Content: " + messageContent);

        Assert.assertEquals(messageContent,message);
    }

    public void openBroadcast() {

        driver.findElement(broadcastAdmin).click();
        driver.findElement(newBroadcastButton).click();

    }


    public void fillInBroadcastDetails(String property, String tenantusername) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click on Property List Broadcast
        wait.until(ExpectedConditions.elementToBeClickable(propertyListBroadcast)).click();

        // Step 2: Search for Property
        driver.findElement(broadcastPropertySearch).sendKeys(property);

        // Step 3: Wait for and click the property option from the dropdown
        WebElement propertyOption = wait.until(ExpectedConditions.elementToBeClickable(broadcastRecipientPropertyOption));
        propertyOption.click();

        // Step 4: Close the property selection popup (if applicable)
        wait.until(ExpectedConditions.elementToBeClickable(closeIcon)).click();

        Thread.sleep(1000);
        // Step 5: Click on Recipient List Broadcast
        wait.until(ExpectedConditions.elementToBeClickable(recipientListBroadcast)).click();

        // Step 6: Search for Tenant Username
        driver.findElement(searchRecipientListBroadcast).sendKeys(tenantusername);

        // Step 7: Locate the recipient option dynamically using its text content
        String recipientXPath = "//li[contains(@role, 'option') and contains(., '" + tenantusername + "')]";
        WebElement recipientOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(recipientXPath)));
        recipientOption.click();

        // Step 8: Close the recipient selection popup (if applicable)
        wait.until(ExpectedConditions.elementToBeClickable(closeIconRecipientList)).click();

        // Step 9: Fill in Broadcast Subject
        wait.until(ExpectedConditions.visibilityOfElementLocated(broadcastSubject)).sendKeys("Welcome to Yarn.");

        // Step 10: Fill in Broadcast Topic
        wait.until(ExpectedConditions.visibilityOfElementLocated(broadcastTopic)).sendKeys(message);

        // Step 11: Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(submitMessage).click();
        System.out.println("Test");

    }

    
}
