package org.example.TestCases;

import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.SubmitRequestSubmitSubscription;
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

public class Test_Case_5_RequestService_Test extends BaseTest {

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
    public void openServicesPageSearch() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        SubmitRequestSubmitSubscription sr = new SubmitRequestSubmitSubscription(driver);
        Thread.sleep(10000);
        lp.myRequestsPage();
        Thread.sleep(10000);
        sr.servicesPageOpenAndSearcFromMyRequests();

    }

    @Test (priority = 1)
    public void submitRequest() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        SubmitRequestSubmitSubscription sr = new SubmitRequestSubmitSubscription(driver);
        sr.openSubmitRequestForm();
        sr.selectServiceAndRequest();
        Thread.sleep(2000);
        sr.assertRequest();
    }
}
