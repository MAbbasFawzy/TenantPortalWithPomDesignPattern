package org.example.TestCases;

import org.example.PageObjects.ContactUs_Admin;
import org.example.PageObjects.Invoices_Admin;
import org.example.PageObjects.LoginAndNavigation;
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

public class Test_Case_15_InvoicePaid extends BaseTest {

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

        Invoices_Admin in = new Invoices_Admin(driver);

        Thread.sleep(4000);

        in.openInvoicesAndAddInvoice();

        adminWindow = driver.getWindowHandle();

    }

    @Test(priority = 1)
    public void openErp() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Thread.sleep(8000);
        erpWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        /*
        driver.get("https://automation.erp.yarncloud.dev/");

         */

        driver.get("https://automation.erp.yarncloud.dev/web#cids=1&menu_id=181&action=295&model=account.move&view_type=list");
    }


    @Test(priority = 2)
    public void openInvoices() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Invoices_Admin in = new Invoices_Admin(driver);

        Thread.sleep(4000);

        in.openInvoiceDetailsAndPost();

        erpWindow = driver.getWindowHandle();


    }

    @Test (priority = 3)
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

    @Test(priority = 4)
    public void checkInvoiceShownInDue() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Invoices_Admin in = new Invoices_Admin(driver);

        in.checkInvoiceInTenant(Invoices_Admin.invoiceNumber);

        tenantWindow = driver.getWindowHandle();

    }

    @Test(priority = 5)

    public void registerPayment() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Invoices_Admin in = new Invoices_Admin(driver);

        driver.switchTo().window(erpWindow);

        in.registerPaymentFromErp();
    }


    @Test(priority = 6)

    public void checkInvoiceInPaid() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Invoices_Admin in = new Invoices_Admin(driver);

        driver.switchTo().window(tenantWindow);

        Thread.sleep(2000);

        driver.navigate().refresh();

        Thread.sleep(2000);

        in.checkInvoiceInPaid(Invoices_Admin.invoiceNumber);


    }

}
