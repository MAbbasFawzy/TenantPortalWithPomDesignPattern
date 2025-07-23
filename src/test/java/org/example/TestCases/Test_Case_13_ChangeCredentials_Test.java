package org.example.TestCases;

import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Profile;
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

public class Test_Case_13_ChangeCredentials_Test {

    WebDriver driver;
    WebDriverWait wait;

    private String baseUrl;
    private String tenantusername;
    private String tenantpassword;
    private String tenant;
    private String tenantUrl;
    private String tenantnewpassword;
    private String confirmtenantnewpassword;
    private String newtenantusername;
    private String confirmnewtenantusername;
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
            tenantnewpassword = properties.getProperty("newpassword");
            confirmtenantnewpassword = properties.getProperty("newpassword");
            newtenantusername = properties.getProperty("newtenantusername");
            confirmnewtenantusername = properties.getProperty("newtenantusername");
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

        Thread.sleep(6000);
        lp.setUsername(tenantusername);
        lp.setPassword(tenantpassword);
        lp.clickLogin();


    }

    @Test(priority = 0)
    public void openProfile() throws InterruptedException {


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Profile pr = new Profile(driver);
        Thread.sleep(6000);
        pr.openProfile();
    }

    @Test(priority = 1)
    public void changeCreds() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Thread.sleep(2000);
        Profile pr = new Profile(driver);
        pr.editPassword(tenantpassword, tenantnewpassword, confirmtenantnewpassword);
        pr.editUsername(newtenantusername, confirmnewtenantusername);
    }

    @Test(priority = 2)
    public void loginWithNewCreds() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Thread.sleep(2000);
        Profile pr = new Profile(driver);
        pr.logoutAndLoginWithNewCreds();

        LoginAndNavigation lp = new LoginAndNavigation(driver);

        Thread.sleep(6000);
        lp.setUsername(newtenantusername);
        lp.setPassword(tenantnewpassword);
        lp.clickLogin();
    }

}
