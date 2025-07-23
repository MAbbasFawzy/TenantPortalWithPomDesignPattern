package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUs_Tenant extends randomGenerator {

    public WebDriver driver;

    // Constructor
    public ContactUs_Tenant(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By categoryList = By.xpath("//span[@role='combobox']");

    By searchCategoryList = By.xpath("//input[@role='searchbox']");

    By listOption = By.xpath("//li[@aria-label='Suggestion']");

    By txt_SubjectInput = By.xpath("//input[@class='p-inputtext p-component flex w-full h-12 p-4 rounded-full border-2 font-normal w-full']");

    By txt_Message = By.xpath("//textarea[@class='w-full']");

    By contactUsAttachment = By.xpath("//input[@id='attachments']");

    By contactUsHistoryButton = By.xpath("//button[normalize-space()='Contact Request History']");

    By submitContactUs = By.xpath("//button[@type='submit']");


    //Action methods

    public void clickCategoryList() {

        driver.findElement(categoryList).click();
    }

    public void enterDataInContactUsForm() throws InterruptedException {

        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

        driver.findElement(listOption).click();

        driver.findElement(txt_SubjectInput).sendKeys("I need help.");

        String message = "I want to add new suggestion.%" + " " + visitor.numbers;
        driver.findElement(txt_Message).sendKeys(message);

        driver.findElement(contactUsAttachment).sendKeys("C:\\Users\\Electronica Care\\Yarn\\Pom\\logo-white.png");

        Thread.sleep(2000);
        driver.findElement(submitContactUs).click();
        Thread.sleep(2000);
        driver.navigate().refresh();

    }

    public void openContactUsHistoryPage() throws InterruptedException {

        Thread.sleep(8000);
        driver.findElement(contactUsHistoryButton).click();

        Thread.sleep(2000);
        // Locate the first row of the contact request history
        WebElement firstRow = driver.findElement(By.xpath("(//a[contains(@class, 'justify-between items-center gap-4 border-t py-2')])[1]"));

        // Extract details from the first row
        String category = firstRow.findElement(By.xpath(".//div[1]")).getText();
        String subject = firstRow.findElement(By.xpath(".//div[2]/p")).getText();
        String message = firstRow.findElement(By.xpath(".//div[3]/p")).getText();
        String createdAt = firstRow.findElement(By.xpath(".//div[4]/p")).getText();

        // Print the extracted details
        System.out.println("Category: " + category);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("Created at: " + createdAt);

    }
}
