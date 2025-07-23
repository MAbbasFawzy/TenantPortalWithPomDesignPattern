package org.example.TestCases;

import org.example.PageObjects.ContactUs_Admin;
import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Visitor;
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

public class Test_Case_11_VisitorsGroup_Blacklisted {

    WebDriver driver;
    WebDriverWait wait;

    public String adminWindow;
    public String tenantWindow;

    private String baseUrl;
    private String tenantusername;
    private String tenantpassword;
    private String tenant;
    private String tenantUrl;
    private String username;
    private String password;
    private String version;

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

        ((JavascriptExecutor) driver).executeScript("localStorage.setItem('app_version', arguments[0]);", version);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        LoginAndNavigation lp = new LoginAndNavigation(driver);

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

        ad.setUsername(username);
        ad.setPassword(password);
        ad.clickLogin();

    }

    @Test(priority = 2)
    public void openVisitorsAndUnwelcome() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Visitor visit = new Visitor(driver);
        visit.openVisitorsAndUnwelcomeVisitor();

        adminWindow = driver.getWindowHandle();

        Thread.sleep(6000);
    }

    @Test(priority = 3)
    public void enterVisitorsGroupWithBlacklistedVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Thread.sleep(6000);

        driver.switchTo().window(tenantWindow);

        LoginAndNavigation lp = new LoginAndNavigation(driver);

        lp.myVisitorsPage();

        Visitor visitor = new Visitor(driver);

        visitor.enterVisitorsGroupBlacklisted(Visitor.documentOption, Visitor.docNumber);

        tenantWindow = driver.getWindowHandle();

        Thread.sleep(6000);

    }

    @Test(priority = 4)
    public void viewBlacklistedVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Thread.sleep(6000);

        driver.switchTo().window(adminWindow);

        Visitor visitor = new Visitor(driver);

        visitor.checkVisitorsFromAdmin();

    }
}
