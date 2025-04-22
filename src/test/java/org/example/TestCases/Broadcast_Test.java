package org.example.TestCases;

import org.example.PageObjects.Connect_Admin;
import org.example.PageObjects.ContactUs_Admin;
import org.openqa.selenium.WebDriver;
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

public class Broadcast_Test {

    WebDriver driver;
    WebDriverWait wait;

    public String adminWindow;
    public String tenantWindow;

    private String baseUrl;
    private String tenantusername;
    private String tenantpassword;
    private String tenant;
    private String tenantUrl;
    private String version;
    private String username;
    private String password;
    private String property;

    @BeforeClass
    public void setup() throws InterruptedException {
        loadProperties();
        initializeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.navigate().to(tenantUrl);
        loginAdmin();
    }


    /*
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
     */


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
            property = properties.getProperty("property");
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


    public void loginAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.get("https://automation.yarncloud.dev/");

        ContactUs_Admin ad = new ContactUs_Admin(driver);

        ad.setUsername(username);
        ad.setPassword(password);
        ad.clickLogin();

    }

    @Test(priority = 0)
    public void openMessages() throws InterruptedException {

        Connect_Admin cd = new Connect_Admin(driver);

        cd.openBroadcast();
        Thread.sleep(2000);
        cd.fillInBroadcastDetails(property, tenantusername);

    }
}
