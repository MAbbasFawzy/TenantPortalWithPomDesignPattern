package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class User_Admin {


    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public User_Admin(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By peopleModule = By.xpath("//span[contains(text(),'Manage People')]");

    By peopleSubModule = By.xpath("/html[1]/body[1]/div[2]/div[1]/nav[1]/div[1]/div[3]/div[3]/div[1]/div[1]/div[3]/div[2]/div[1]/ul[1]/li[1]/div[1]/a[1]");

    By search = By.xpath("//input[@placeholder='Search']");

    By viewButton = By.xpath("//td[contains(text(), 'yarn.user.tenant')]/ancestor::tr//a[contains(@class, 'btn-outline') and contains(@class, 'btn-primary')]");

    By userAccountInfo = By.xpath("//a[normalize-space()='User Account Info']");

    By userCredentials = By.xpath("//button[@class='btn btn-primary']");

    By newusername = By.xpath("//input[@placeholder='Enter the new username']");

    By confirmnewusername = By.xpath("//input[@id='confirmUsername']");

    By submitusername = By.xpath("//div[4]//button[1]");

    By changepassword = By.xpath("//a[normalize-space()='Change Password']");

    By newpassword = By.xpath("//input[@placeholder='Enter the new password']");

    By confirmnewpassword = By.xpath("//input[@id='confirm-password']");

    By submitpassword = By.xpath("//div[@class='p-dialog-mask p-component-overlay p-component-overlay-enter']//div[3]//button[1]");

    // Action methods

    public void openPeopleModuleAndViewUser(String tenantusername) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Step 1: Click on the People Module
        WebElement peopleModuleElement = wait.until(ExpectedConditions.elementToBeClickable(peopleModule));
        peopleModuleElement.click();

        // Step 2: Click on the People Submodule
        WebElement peopleSubModuleElement = wait.until(ExpectedConditions.elementToBeClickable(peopleSubModule));
        peopleSubModuleElement.click();

        Thread.sleep(10000);

        // Step 1: Locate the search box and enter the tenant username
        driver.findElement(search).sendKeys("yarn.user.tenant");


        // Wait for the search results to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'yarn.user.tenant')]")));

        Thread.sleep(10000);

        // Scroll up by a specific number of pixels
        JavascriptExecutor js = (JavascriptExecutor)driver;

        // Scroll to the top of the page
        js.executeScript("window.scrollTo(0, 0)");


        Thread.sleep(10000);
        // Step 2: Locate the "View" button for the tenant
        driver.findElement(viewButton).click();

    }

    public void changeCreds(String newtenantuser, String newtenantpass) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        driver.findElement(userAccountInfo).click();

        driver.findElement(userCredentials).click();

        Thread.sleep(10000);

        driver.findElement(newusername).sendKeys(newtenantuser);

        Thread.sleep(10000);

        driver.findElement(confirmnewusername).sendKeys(newtenantuser);
        Thread.sleep(10000);

        driver.findElement(submitusername).click();

        Thread.sleep(10000);

        driver.navigate().refresh();

        Thread.sleep(10000);
        driver.findElement(userAccountInfo).click();
        Thread.sleep(10000);

        driver.findElement(userCredentials).click();
        Thread.sleep(10000);

        driver.findElement(changepassword).click();
        Thread.sleep(10000);

        driver.findElement(newpassword).sendKeys(newtenantpass);
        Thread.sleep(10000);

        driver.findElement(confirmnewpassword).sendKeys(newtenantpass);
        Thread.sleep(10000);

        driver.findElement(submitpassword).click();

        Thread.sleep(6000);

    }


}
