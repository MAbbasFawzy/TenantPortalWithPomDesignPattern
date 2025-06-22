package org.example.TestCases;

import com.reporting.ClickUpNotifier;
import org.example.PageObjects.LoginAndNavigation;
import org.openqa.selenium.*;
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

public class Test_Case_1_LoginAndNavigation_Test extends ClickUpNotifier {

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

    private String generateTestResultsMessage() {
        // Logic to generate a summary of test results
        // You can read the test-output/index.html or use TestNG's IReporter interface
        return "Test results summary: ..."; // Replace with actual results
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

    }


    @Test
    public void checkServicesPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        System.out.println("We logged in!....");

        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.servicesPage();
    }

    @Test
    public void checkMyInvoicesPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myInvoicesPage();
    }

    @Test
    public void checkMyVisitorsPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myVisitorsPage();
    }

    @Test
    public void checkMyViolationsPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myViolationsPage();
    }

    @Test
    public void checkMyDocumentsPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myDocumentsPage();
    }

    @Test
    public void checkMyDependentsPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myDependents();
    }

    @Test
    public void checkMyPetsPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myPetsPage();
    }

    @Test
    public void checkMyVehiclesPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myVehiclesPage();
    }

    @Test
    public void checkContactUsPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);

        try {
            lp.contactUsPage();
        } catch (StaleElementReferenceException e) {
            // Retry the action if a StaleElementReferenceException occurs
            System.out.println("StaleElementReferenceException caught. Retrying...");
            lp = new LoginAndNavigation(driver); // Re-instantiate the page object
            lp.contactUsPage(); // Retry the action
        }
    }

    @Test
    public void checkCommunityChatPageOpen() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.communityNewsPage();
    }

    @Test
    public void checkMyRequestsPageOpen() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        LoginAndNavigation lp = new LoginAndNavigation(driver);
        lp.myRequestsPage();
    }
}
