package org.example.TestCases;

import org.example.PageObjects.ContactUs_Admin;
import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Violations_Admin;
import org.example.base.BaseTest;
import org.openqa.selenium.*;
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

public class Test_Case_16_Violations_Admin_Test extends BaseTest {


    @BeforeClass
    public void loginSetup() throws InterruptedException {
        loginAdmin();
    }


    public void loginAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.get("https://automation.yarncloud.dev/");

        ContactUs_Admin ad = new ContactUs_Admin(driver);
        Thread.sleep(8000);
        ad.setUsername(username);
        ad.setPassword(password);
        ad.clickLogin();



    }

    @Test(priority = 0)
    public void openViolations() throws InterruptedException {

        Violations_Admin va = new Violations_Admin(driver);
        Thread.sleep(8000);
        va.openAddNewViolation();

    }

    @Test(priority = 1)
    public void fillInViolationDetails() throws InterruptedException {

        Violations_Admin va = new Violations_Admin(driver);
        va.addViolationDetails();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void loginTenant() throws InterruptedException {

        tenantWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://automation.yarncloud.dev/tenant/auth/login");

        ((JavascriptExecutor) driver).executeScript("localStorage.setItem('app_version', arguments[0]);", version);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        LoginAndNavigation lp = new LoginAndNavigation(driver);
        Thread.sleep(8000);
        lp.setUsername(tenantusername);

        lp.setPassword(tenantpassword);

        lp.clickLogin();

        lp.myViolationsPage();

    }

    @Test(priority = 3)
    public void checkViolationDataAdded() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Violations_Admin va = new Violations_Admin(driver);

        va.checkViolationAddedInTenant();

    }
}
