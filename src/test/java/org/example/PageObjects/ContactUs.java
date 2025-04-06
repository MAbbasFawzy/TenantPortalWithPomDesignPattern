package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUs extends randomGenerator {

    public WebDriver driver;

    // Constructor
    public ContactUs(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By categoryList = By.xpath("//span[@role='combobox']");

    By searchCategoryList = By.xpath("//input[@role='searchbox']");

    By listOption = By.xpath("//li[@aria-label='Suggestion']");

    By txt_SubjectInput = By.xpath("//input[@class='p-inputtext p-component flex w-full h-10 rounded-[40px] border-2 w-full']");

    By txt_Message = By.xpath("//textarea[@class='w-full']");

    By contactUsAttachment = By.xpath("//input[@id='attachments']");

    By contactUsHistoryButton = By.xpath("//button[normalize-space()='Contact Request History']");




    //Action methods

    public void clickCategoryList() {

        driver.findElement(categoryList).click();
    }

    public void enterDataInContactUsForm() {

        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

        driver.findElement(listOption).click();

        driver.findElement(txt_SubjectInput).sendKeys("I need help.");

        String message = "I want to add new suggestion.%" + " " + visitor.numbers;
        driver.findElement(txt_Message).sendKeys(message);

        driver.findElement(contactUsAttachment).sendKeys("C:\\Users\\Electronica Care\\Yarn\\Pom\\logo-white.png");

    }

    public void openContactUsHistoryPage() {

        driver.findElement(contactUsHistoryButton).click();

        /*
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

         */
    }
}
