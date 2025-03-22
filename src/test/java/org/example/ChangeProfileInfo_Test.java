package org.example;

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

public class ChangeProfileInfo_Test {

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);

        Thread.sleep(6000);
        lp.setUsername(tenantusername);
        lp.setPassword(tenantpassword);
        lp.clickLogin();
    }

    @Test(priority = 0)
    public void openProfile() throws InterruptedException {

        Thread.sleep(6000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        Profile pr = new Profile(driver);

        pr.editProfileInfo();
    }

}
