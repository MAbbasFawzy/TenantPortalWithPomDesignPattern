package org.example.TestCases;

import org.example.PageObjects.ContactUs_Admin;
import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Request_Admin;
import org.example.PageObjects.Visitor;
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

public class Test_Case_8_VisitorResubmit extends BaseTest {

    @BeforeClass
    public void loginSetup() throws InterruptedException {
        login();
    }


    public void login() throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("localStorage.setItem('app_version', arguments[0]);", version);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        LoginAndNavigation lp = new LoginAndNavigation(driver);
        Thread.sleep(8000);
        lp.setUsername(tenantusername);

        lp.setPassword(tenantpassword);

        lp.clickLogin();


        lp.myVisitorsPage();

        tenantWindow = driver.getWindowHandle();

    }

    @Test(priority = 0)
    public void addVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Visitor visitor = new Visitor(driver);
        visitor.addVisitor();

    }

    @Test(priority = 1)
    public void loginAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        adminWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://automation.yarncloud.dev/");

        ContactUs_Admin ad = new ContactUs_Admin(driver);
        Thread.sleep(8000);
        ad.setUsername(username);
        ad.setPassword(password);
        ad.clickLogin();

    }

    @Test(priority = 2)
    public void openVisitorsAndExpire() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Thread.sleep(8000);
        Visitor visit = new Visitor(driver);
        Thread.sleep(8000);
        visit.openVisitorsAndExpireVisitor();

        adminWindow = driver.getWindowHandle();


    }

    @Test(priority = 3)
    public void resubmitVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.switchTo().window(tenantWindow);

        LoginAndNavigation lp = new LoginAndNavigation(driver);

        lp.myVisitorsPage();

        Visitor visitor = new Visitor(driver);

        visitor.resubmitVisitor(Visitor.documentOption, Visitor.docNumber);



    }


}
