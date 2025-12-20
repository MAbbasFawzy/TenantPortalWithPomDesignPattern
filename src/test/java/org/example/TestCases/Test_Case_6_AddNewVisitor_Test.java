package org.example.TestCases;

import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Visitor;
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

public class Test_Case_6_AddNewVisitor_Test extends BaseTest {

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Short timeout
        try {
            WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Skip']")));
            skipButton.click();

            WebElement okayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Okay']")));
            okayButton.click();

            System.out.println("✅ Skip button appeared and clicked.");
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("⏭️ Skip button did not appear within 5 seconds, continuing...");
        }

        lp.myVisitorsPage();

    }

    @Test(priority = 0)
    public void addVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Visitor visitor = new Visitor(driver);

        visitor.addVisitor();
    }
}
