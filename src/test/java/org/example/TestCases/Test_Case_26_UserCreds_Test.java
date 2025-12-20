package org.example.TestCases;

import org.example.PageObjects.ContactUs_Admin;
import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.User_Admin;
import org.example.base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
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

public class Test_Case_26_UserCreds_Test extends BaseTest {


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
        Thread.sleep(10000);


    }

    @Test(priority = 0)
    public void openPeopleModule() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Thread.sleep(10000);
        User_Admin ud = new User_Admin(driver);
        Thread.sleep(10000);
        ud.openPeopleModuleAndViewUser(tenantusername);
        Thread.sleep(10000);
        ud.changeCreds(newtenantusername, newtenantpassword);
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void loginWithNewCreds() throws InterruptedException {

        Thread.sleep(10000);
        tenantWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://automation.yarncloud.dev/tenant/auth/login");

        ((JavascriptExecutor) driver).executeScript("localStorage.setItem('app_version', arguments[0]);", version);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        LoginAndNavigation lp = new LoginAndNavigation(driver);
        Thread.sleep(8000);
        lp.setUsername(newtenantusername);

        lp.setPassword(newtenantpassword);

        lp.clickLogin();
    }
}
