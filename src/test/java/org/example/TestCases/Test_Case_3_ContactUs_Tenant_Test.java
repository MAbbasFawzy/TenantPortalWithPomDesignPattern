package org.example.TestCases;

import org.example.PageObjects.ContactUs_Tenant;
import org.example.PageObjects.LoginAndNavigation;
import org.example.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class Test_Case_3_ContactUs_Tenant_Test extends BaseTest {

    @BeforeClass
    public void loginSetup() throws InterruptedException {
        login();
    }

    public void login() throws InterruptedException {

        ((JavascriptExecutor) driver)
                .executeScript("localStorage.setItem('app_version', arguments[0]);", version);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginAndNavigation lp = new LoginAndNavigation(driver);

        Thread.sleep(3000);
        lp.setUsername(tenantusername);
        lp.setPassword(tenantpassword);
        lp.clickLogin();
        Thread.sleep(3000);
    }


    @Test (priority = 0)
    public void checkContactUsPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.contactUsPage();
    }

    @Test (priority = 1)
    public void openContactUsPage() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        ContactUs_Tenant cu = new ContactUs_Tenant(driver);

        Thread.sleep(4000);
        cu.clickCategoryList();
    }

    @Test (priority = 2)
    public void enterDataInForm() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ContactUs_Tenant cu = new ContactUs_Tenant(driver);

        Thread.sleep(4000);
        cu.enterDataInContactUsForm();
        cu.openContactUsHistoryPage();

    }
}
