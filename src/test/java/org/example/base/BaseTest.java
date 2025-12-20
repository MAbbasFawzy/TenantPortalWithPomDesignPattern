package org.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties properties;

    protected String adminWindow;
    protected String tenantWindow;
    protected String erpWindow;

    protected String tenantUrl;
    protected String tenantusername;
    protected String tenantpassword;
    protected String tenant;
    protected String username;
    protected String password;
    protected String version;

    protected String tenantnewpassword;
    protected String confirmtenantnewpassword;
    protected String newtenantusername;
    protected String confirmnewtenantusername;
    protected String newtenantpassword;
    protected String property;


    @BeforeClass(alwaysRun = true)
    public void setUp() {

        loadProperties();
        initializeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(tenantUrl);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================
    // Load config.properties
    // =========================
    protected void loadProperties() {
        properties = new Properties();

        try (InputStream input =
                     getClass().getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found in src/test/resources");
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

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // =========================
    // WebDriver initialization
    // =========================
    protected void initializeDriver() {

        String browser = System.getProperty(
                "browser",
                properties.getProperty("browser", "chrome")
        );

        boolean headless = Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        properties.getProperty("headless", "true")
                )
        );

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");

            if (headless) {
                options.addArguments(
                        "--headless=new",
                        "--no-sandbox",
                        "--disable-dev-shm-usage"
                );
            }

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("-headless");
            }

            driver = new FirefoxDriver(options);

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

}
