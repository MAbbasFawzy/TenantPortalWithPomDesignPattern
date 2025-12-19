package org.example.TestCases;

import org.example.PageObjects.LoginAndNavigation;
import org.example.base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class Test_Case_1_LoginAndNavigation_Test extends BaseTest {

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

    @Test
    public void checkServicesPageOpen() {
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.servicesPage();
    }

    @Test
    public void checkMyInvoicesPageOpen() {
        new LoginAndNavigation(driver).myInvoicesPage();
    }

    @Test
    public void checkMyVisitorsPageOpen() {
        new LoginAndNavigation(driver).myVisitorsPage();
    }

    @Test
    public void checkMyViolationsPageOpen() {
        new LoginAndNavigation(driver).myViolationsPage();
    }

    @Test
    public void checkMyDocumentsPageOpen() {
        new LoginAndNavigation(driver).myDocumentsPage();
    }

    @Test
    public void checkMyDependentsPageOpen() {
        new LoginAndNavigation(driver).myDependents();
    }

    @Test
    public void checkMyPetsPageOpen() {
        new LoginAndNavigation(driver).myPetsPage();
    }

    @Test
    public void checkMyVehiclesPageOpen() {
        new LoginAndNavigation(driver).myVehiclesPage();
    }

    @Test
    public void checkContactUsPageOpen() {

        LoginAndNavigation lp = new LoginAndNavigation(driver);
        try {
            lp.contactUsPage();
        } catch (StaleElementReferenceException e) {
            lp = new LoginAndNavigation(driver);
            lp.contactUsPage();
        }
    }

    @Test
    public void checkCommunityChatPageOpen() {
        new LoginAndNavigation(driver).communityNewsPage();
    }

    @Test
    public void checkMyRequestsPageOpen() {
        new LoginAndNavigation(driver).myRequestsPage();
        assertTrue(true);
    }
}
