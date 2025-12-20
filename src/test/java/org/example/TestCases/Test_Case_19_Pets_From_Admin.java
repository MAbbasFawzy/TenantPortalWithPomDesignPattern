package org.example.TestCases;

import org.example.PageObjects.ContactUs_Admin;
import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Tenants_Admin;
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

public class Test_Case_19_Pets_From_Admin extends BaseTest {

    @BeforeClass
    public void loginSetup() throws InterruptedException {
        loginAdmin();
    }


    public void loginAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        adminWindow = driver.getWindowHandle();

        driver.get("https://automation.yarncloud.dev/");

        ContactUs_Admin ad = new ContactUs_Admin(driver);
        Thread.sleep(8000);
        ad.setUsername(username);
        ad.setPassword(password);
        ad.clickLogin();

    }

    @Test(priority = 0)
    public void openTenantsAndView() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Tenants_Admin ta = new Tenants_Admin(driver);

        Thread.sleep(6000);

        ta.openTenantsAndView();


    }

    @Test(priority = 1)
    public void addPetsFromAdmin() throws InterruptedException {

        Tenants_Admin ta = new Tenants_Admin(driver);

        Thread.sleep(8000);

        ta.addPetsFromAdminAndGetPetData();

        adminWindow = driver.getWindowHandle();

    }

    @Test (priority = 2)
    public void login() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

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
    }

    @Test(priority = 3)
    public void checkPetsDataFromTenant() throws InterruptedException {

        Tenants_Admin ta = new Tenants_Admin(driver);
        Thread.sleep(8000);
        ta.checkPetFromTenant(Tenants_Admin.nameOfPet);
        tenantWindow = driver.getWindowHandle();

    }

    @Test(priority = 4)
    public void deletePetsFromAdmin() throws InterruptedException {

        driver.switchTo().window(adminWindow);


        Tenants_Admin ta = new Tenants_Admin(driver);
        Thread.sleep(10000);
        ta.deletePetsFromAdmin();


    }
}
