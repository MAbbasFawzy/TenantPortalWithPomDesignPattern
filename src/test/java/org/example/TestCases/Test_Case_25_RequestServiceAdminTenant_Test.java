package org.example.TestCases;

import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Request_Admin;
import org.example.PageObjects.SubmitRequestSubmitSubscription;
import org.example.base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class Test_Case_25_RequestServiceAdminTenant_Test extends BaseTest {

    @BeforeClass
    public void loginSetup() throws InterruptedException {
        login();
    }


    public void login() throws InterruptedException {

        tenantWindow = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript("localStorage.setItem('app_version', arguments[0]);", version);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        Thread.sleep(8000);
        lp.setUsername(tenantusername);
        lp.setPassword(tenantpassword);
        lp.clickLogin();


    }

    @Test(priority = 0)
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

    @Test(priority = 2)
    public void loginAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        adminWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://automation.yarncloud.dev/");

        Request_Admin ra = new Request_Admin(driver);
        Thread.sleep(8000);
        ra.setUsername(username);

        ra.setPassword(password);

        ra.clickLogin();

    }

    @Test(priority = 3)
    public void openRequests() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Request_Admin ra = new Request_Admin(driver);

        Thread.sleep(8000);

        ra.openRequests();

        Thread.sleep(8000);

        ra.searchRequestAndView();

        Thread.sleep(2000);
        adminWindow = driver.getWindowHandle();

    }

    @Test(priority = 4)
    public void checkInProgressTenant() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        Thread.sleep(2000);

        Request_Admin ra = new Request_Admin(driver);

        ra.checkStatusInProgress();

        tenantWindow = driver.getWindowHandle();
    }

    @Test(priority = 5)
    public void pauseRequestAdmin() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(adminWindow);
        Request_Admin ra = new Request_Admin(driver);
        ra.pauseRequest();

        adminWindow = driver.getWindowHandle();
    }

    @Test(priority = 6)
    public void checkPauseTenant() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        Request_Admin ra = new Request_Admin(driver);

        ra.checkPauseTenant();

    }

    @Test(priority = 7)
    public void cancelFromAdmin() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(adminWindow);
        Request_Admin ra = new Request_Admin(driver);
        ra.cancelRequest();

        adminWindow = driver.getWindowHandle();

    }

    @Test(priority = 8)
    public void checkCancelledTenant() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        Request_Admin ra = new Request_Admin(driver);

        ra.checkCancelledRequestTenant();
    }

    @Test(priority = 9)

    public void completeRequestAdmin() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(adminWindow);
        Request_Admin ra = new Request_Admin(driver);
        ra.completeRequest();

        adminWindow = driver.getWindowHandle();

    }

    @Test(priority = 10)
    public void checkRequestCompleteTenant() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        Request_Admin ra = new Request_Admin(driver);

        ra.checkCompleteRequestTenant();

    }
}
