package org.example.TestCases;

import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Request_Admin;
import org.example.PageObjects.SubmitRequestSubmitSubscription;
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

public class Test_Case_24_SubscriptionStatusChange {

    WebDriver driver;
    WebDriverWait wait;

    private String baseUrl;
    private String tenantusername;
    private String tenantpassword;
    private String tenant;
    private String tenantUrl;
    private String version;
    private String username;
    private String password;

    public String adminWindow;
    public String tenantWindow;

    @BeforeClass
    public void setup() throws InterruptedException {
        loadProperties();
        initializeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.navigate().to(tenantUrl);
        login();
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    public void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
            tenantUrl = properties.getProperty("tenant.url");
            tenantusername = properties.getProperty("tenantusername");
            tenantpassword = properties.getProperty("tenantpassword");
            tenant = properties.getProperty("tenant");
            password = properties.getProperty("password");
            username = properties.getProperty("username");
            version = properties.getProperty("version");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeDriver() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            String browser = properties.getProperty("browser");
            if ("chrome".equalsIgnoreCase(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equalsIgnoreCase(browser)) {
                driver = new FirefoxDriver();
            } else {
                System.out.println("Unsupported browser: " + browser);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        SubmitRequestSubmitSubscription sr = new SubmitRequestSubmitSubscription(driver);

        lp.servicesPage();
        Thread.sleep(8000);
        sr.servicesPageOpenAndSearch();

    }

    @Test(priority = 1)
    public void submitSubscription() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        SubmitRequestSubmitSubscription sr = new SubmitRequestSubmitSubscription(driver);

        sr.selectServiceAndSubscribe();
        Thread.sleep(2000);
        sr.checkSubscriptionAdded();

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
    public void openSubscriptionsAndView() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Thread.sleep(10000);
        Request_Admin ra = new Request_Admin(driver);
        Thread.sleep(10000);
        ra.openSubscriptions();

    }

    @Test(priority = 4)
    public void startSubscription() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Request_Admin ra = new Request_Admin(driver);
        Thread.sleep(8000);
        ra.startSubscription();

        Thread.sleep(2000);

        adminWindow = driver.getWindowHandle();

    }

    @Test(priority = 5)
    public void checkActiveTenant() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        Request_Admin ra = new Request_Admin(driver);

        ra.checkActiveTenant();

        tenantWindow = driver.getWindowHandle();
    }

    @Test(priority = 6)
    public void pauseSubAdmin() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(adminWindow);
        Request_Admin ra = new Request_Admin(driver);
        ra.pauseSubsriptionAdmin();

        adminWindow = driver.getWindowHandle();

    }

    @Test(priority = 7)
    public void checkPauseTenant() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        Request_Admin ra = new Request_Admin(driver);

        ra.checkPauseSubTenant();

        tenantWindow = driver.getWindowHandle();
    }

    @Test(priority = 9)
    public void endSubAdmin() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(adminWindow);
        Request_Admin ra = new Request_Admin(driver);
        ra.endSubsriptionAdmin();

        adminWindow = driver.getWindowHandle();

    }


    @Test(priority = 10)
    public void checkEndedTenant() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        Request_Admin ra = new Request_Admin(driver);

        ra.checkEndSubTenant();

        tenantWindow = driver.getWindowHandle();
    }

    @Test(priority = 11)
    public void terminateSubAdmin() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(adminWindow);
        Request_Admin ra = new Request_Admin(driver);
        ra.terminateSubscriptionAdmin();

        adminWindow = driver.getWindowHandle();

    }

    @Test(priority = 12)
    public void checkTerminatedSubTenant() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        Request_Admin ra = new Request_Admin(driver);

        ra.checkTerminatedSubTenant();

        tenantWindow = driver.getWindowHandle();

    }

    @Test(priority = 13)
    public void checkCancelledAdmin() throws InterruptedException {

        Thread.sleep(2000);

        driver.switchTo().window(adminWindow);
        Request_Admin ra = new Request_Admin(driver);
        ra.cancelledSubscriptionAdmin();

        adminWindow = driver.getWindowHandle();
    }

    @Test(priority = 14)
    public void checkCancelledTenant() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.switchTo().window(tenantWindow);

        driver.navigate().refresh();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        Request_Admin ra = new Request_Admin(driver);

        ra.checkCancelledSubTenant();

        tenantWindow = driver.getWindowHandle();

    }

}
