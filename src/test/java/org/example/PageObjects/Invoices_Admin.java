package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.WebDriver;

public class Invoices_Admin {

    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public Invoices_Admin(WebDriver driver) {
        this.driver = driver;
    }


    // Locators


    // Action methods

}
