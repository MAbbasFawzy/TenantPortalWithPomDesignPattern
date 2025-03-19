package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LoginAndNavigation {

    public WebDriver driver;


    // Constructor
    public LoginAndNavigation(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By txt_email = By.xpath("//input[@data-pc-name='inputtext']");

    By txt_password = By.xpath("//input[@placeholder='Password']");

    By btn_loginButton = By.xpath("//button[normalize-space()='Log in']");

    By txt_tenantUsername = By.xpath("//span[contains(text(), 'yarn.user.tenant')]");

    By txt_servicesPage = By.linkText("Services");

    By txt_myRequestsPage = By.linkText("My Requests");

    By txt_myInvoicesPage = By.linkText("My Invoices");

    By txt_myVisitorsPage = By.linkText("My Visitors");

    By txt_myViolations = By.linkText("My Violations");

    By txt_myDocuments = By.linkText("My Documents");

    By txt_myDependents = By.linkText("My Dependents");

    By txt_myPets = By.linkText("My Pets");

    By txt_myVehicles = By.linkText("My Vehicles");

    By txt_contactUs = By.linkText("Contact us");

    By txt_communityChat = By.linkText("Community Chat");


    //Action methods

    public void setUsername(String username) {
        driver.findElement(txt_email).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(txt_password).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(btn_loginButton).click();
    }

    public void checkUsername () {
        driver.findElement(txt_tenantUsername).getText();
    }


    public void servicesPage() {
        driver.findElement(txt_servicesPage).click();
    }

    public void myRequestsPage() {
        driver.findElement(txt_myRequestsPage).click();
    }

    public void myInvoicesPage () {
        driver.findElement(txt_myInvoicesPage).click();
    }

    public void myVisitorsPage () {
        driver.findElement(txt_myVisitorsPage).click();
    }

    public void myViolationsPage () {
        driver.findElement(txt_myViolations).click();
    }

    public void myDocumentsPage () {
        driver.findElement(txt_myDocuments).click();
    }

    public void myDependents() {
        driver.findElement(txt_myDependents).click();
    }

    public void myPetsPage () {
        driver.findElement(txt_myPets).click();
    }

    public void myVehiclesPage () {
        driver.findElement(txt_myVehicles).click();
    }

    public void contactUsPage () {
        driver.findElement(txt_contactUs).click();
    }

    public void communityNewsPage () {
        driver.findElement(txt_communityChat).click();
    }
}
