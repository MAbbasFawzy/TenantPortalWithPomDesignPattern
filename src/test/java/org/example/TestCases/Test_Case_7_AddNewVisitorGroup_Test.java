package org.example.TestCases;

import org.example.PageObjects.LoginAndNavigation;
import org.example.PageObjects.Visitor;
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

public class Test_Case_7_AddNewVisitorGroup_Test {

    WebDriver driver;
    WebDriverWait wait;

    private String baseUrl;
    private String tenantusername;
    private String tenantpassword;
    private String tenant;
    private String tenantUrl;
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
        Thread.sleep(2000);
        visitor.addNewVisitorsGroup();

    }
}
