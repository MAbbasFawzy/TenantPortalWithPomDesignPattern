package org.example.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ContactUs_Admin {

    public WebDriver driver;
    String contactusmessage;

    // Constructor
    public ContactUs_Admin(WebDriver driver) {
        this.driver = driver;
    }

    //Locators


    By usernameAdmin = By.xpath("//input[@placeholder='Username']");

    By passwordAdmin = By.xpath("//input[@placeholder='Password']");

    By loginButton = By.xpath("//button[@type='submit']");

    By tenantRequests = By.xpath("//span[contains(text(),'Tenant Requests')]");

    By contactUsLink = By.xpath("//a[@href='/contact-us']");

    By viewContactUsMessage = By.xpath(".//td[last()]/a[contains(text(),'View')]");

    By requestDetails = By.xpath("//div[@class='whitespace-break-spaces']");


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

    public void openContactUsPage() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(tenantRequests).click();

        driver.findElement(contactUsLink).click();

    }

    public void openContactUsRequestDetails() throws InterruptedException {

        // Locate the table
        WebElement table = driver.findElement(By.id("ContactUsTable_content_table"));

        // Locate all rows in the table body
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

        // Check if there are any rows
        if (!rows.isEmpty()) {
            // Get the last row
            WebElement lastRow = rows.get(rows.size() - 1);


            // Locate the "View" button in the last row
            driver.findElement(viewContactUsMessage).click();
            Thread.sleep(1000);

        } else {
            System.out.println("No rows found in the table.");
        }
        driver.findElement(requestDetails).getText();
        System.out.println(driver.findElement(requestDetails).getText());
    }
}
