package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Profile {

    public WebDriver driver;

    // Constructor
    public Profile(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By userProfile = By.xpath("//img[@alt='Avatar']");

    By profileSettings = By.xpath("//button[normalize-space()='Settings']");

    By editProfile = By.xpath("//button[normalize-space()='Edit profile']");

    By changePassword = By.xpath("//a[contains(@class, 'flex items-center justify-between') and contains(text(), 'Change password')]");

    By changeUsername = By.xpath("//a[@class='flex items-center justify-between me-2 px-2 pb-3 mb-2']");

    By currentPassword = By.xpath("//input[@id='current-password']");

    By newPassword = By.xpath("//input[@id='password-input']");

    By confirmNewPassword = By.xpath("//input[@id='confirm-password']");

    By submitNewPassword = By.xpath("//button[@type='submit']");

    By newUsername = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/input[1]");

    By confirmNewUsername = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/div[3]/input[1]");

    By logoutButton = By.xpath("//div[@class='flex justify-between items-center h-22 border-b']//a[3]//*[name()='svg']");

    By submitUsername = By.xpath("//button[@type='submit']");



    // Action Methods
    public void openProfile() {

        driver.findElement(userProfile).click();
        driver.findElement(profileSettings).click();
        driver.findElement(editProfile).click();
    }

    public void editPassword(String oldpassword, String newpassword, String confirmnewpassword) throws InterruptedException {
        Thread.sleep(4000);
        driver.navigate().refresh();
        driver.findElement(changePassword).click();

        driver.findElement(currentPassword).sendKeys(oldpassword);
        driver.findElement(newPassword).sendKeys(newpassword);
        driver.findElement(confirmNewPassword).sendKeys(confirmnewpassword);

        driver.findElement(submitNewPassword).click();
        Thread.sleep(2000);
        driver.navigate().refresh();
    }

    public void editUsername(String newusername, String confirmnewusername) {

        driver.findElement(changeUsername).click();
        driver.findElement(newUsername).sendKeys(newusername);
        driver.findElement(confirmNewUsername).sendKeys(confirmnewusername);
        driver.findElement(submitUsername).click();

    }

    public void logoutAndLoginWithNewCreds() throws InterruptedException {

        Thread.sleep(6000);
        driver.findElement(logoutButton).click();

    }
}
