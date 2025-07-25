package org.example.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Profile {

    public WebDriver driver;

    // Constructor
    public Profile(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By userProfile = By.xpath("//img[@alt='Avatar']");

    By profileSettings = By.xpath("//button[@class='button md:whitespace-nowrap card ms-auto hidden sm:flex text-black font-normal justify-center items-center rounded-full py-2']");

    By editProfile = By.xpath("//button[normalize-space()='Edit profile']");

    By changePassword = By.xpath("//a[@class='card flex items-center rounded-3xl yarn-shadow h-20 font-400']");

    By changeUsername = By.xpath("//a[@class='flex items-center card rounded-3xl yarn-shadow h-20 font-400']");

    By currentPassword = By.xpath("//input[@id='current-password']");

    By newPassword = By.xpath("//input[@id='password-input']");

    By confirmNewPassword = By.xpath("//input[@id='confirm-password']");

    By submitNewPassword = By.xpath("//button[@type='submit']");

    By newUsername = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]");

    By confirmNewUsername = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/input[1]");

    By logoutButton = By.xpath("//div[@class='flex justify-between items-center h-22 border-b']//a[3]//*[name()='svg']");

    By submitUsername = By.xpath("//button[@type='submit']");

    By editProfileButton = By.xpath("//button[normalize-space()='Edit profile']");

    By userInfo = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[4]/input[1]");

    By submitUserInfo = By.xpath("//button[@type='submit']");

    // Action Methods
    public void openProfile() throws InterruptedException {

        Thread.sleep(500);
        driver.findElement(userProfile).click();
        Thread.sleep(500);
        driver.findElement(profileSettings).click();

    }

    public void editPassword(String oldpassword, String newpassword, String confirmnewpassword) throws InterruptedException {
        Thread.sleep(500);
        driver.navigate().refresh();
        driver.findElement(changePassword).click();
        Thread.sleep(6000);
        driver.findElement(currentPassword).sendKeys(oldpassword);
        driver.findElement(newPassword).sendKeys(newpassword);
        driver.findElement(confirmNewPassword).sendKeys(confirmnewpassword);

        driver.findElement(submitNewPassword).click();
        Thread.sleep(2000);
        driver.navigate().refresh();
    }

    public void editUsername(String newusername, String confirmnewusername) throws InterruptedException {


        Thread.sleep(2000);
        driver.findElement(changeUsername).click();

        Thread.sleep(2000);
        driver.findElement(newUsername).sendKeys(newusername);

        Thread.sleep(2000);
        driver.findElement(confirmNewUsername).sendKeys(confirmnewusername);

        Thread.sleep(2000);
        driver.findElement(submitUsername).click();

    }

    public void logoutAndLoginWithNewCreds() throws InterruptedException {

        Thread.sleep(6000);
        driver.findElement(logoutButton).click();

    }

    public void editProfileInfo() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.findElement(userProfile).click();
        driver.findElement(editProfileButton).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Thread.sleep(6000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.END).build().perform();


        driver.findElement(userInfo).sendKeys("Test");
        Thread.sleep(6000);
        driver.findElement(submitUserInfo).click();

    }
}
