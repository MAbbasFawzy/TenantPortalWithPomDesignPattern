package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class testing {

    private static final Logger log = LoggerFactory.getLogger(testing.class);
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.navigate().to("http://uat-resorsa.faltechnology.solutions/");
    }

    @Test (priority = 0)
    public void openURL() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("admin@assets.com.sa");

        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("Aa@123456");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/vertical-layout/div/content/div/app-login/div/div/div[2]/div/form/button"));
        loginButton.click();

        WebElement dropList = driver.findElement(By.xpath("//*[@id=\"OrgId\"]/div/div/div[2]/input"));
        dropList.click();

        WebElement option = driver.findElement(By.xpath("/html/body/app-root/vertical-layout/div/content/div/app-login/div/div/div[2]/div/form/div[3]/ng-select/ng-dropdown-panel/div/div[2]/div[3]"));
        option.click();

        loginButton.click();

    }

    @Test (priority = 1)
    public void openAssetsPage() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        WebElement assetsPageLink = driver.findElement(By.xpath("//a[@href='#/org/assets']//span[@class='menu-title text-truncate ng-star-inserted'][contains(text(),'الأصول')]"));
        assetsPageLink.click();



    }

    @Test (priority = 2)

    public void deleteAsset() throws InterruptedException {

        /*
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        // Locate all checkboxes in the datatable
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // Iterate through the list and click each checkbox
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) { // Check if the checkbox is not already selected
                checkbox.click(); // Click the checkbox
            }
        }

        Thread.sleep(4000);

        // Scroll to the top of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        // Optionally, you can add a wait to observe the scroll effect
        Thread.sleep(4000); // Wait for 2 seconds

        WebElement actionList = driver.findElement(By.xpath("//button[@type='button' and contains(@class, 'dropdown-toggle')]"));
        actionList.click();

        Thread.sleep(2000);
        WebElement deleteAction = driver.findElement(By.xpath("//a[contains(text(),'حذف')]"));
        deleteAction.click();

        Thread.sleep(2000);
        WebElement confirmDelete = driver.findElement(By.xpath("//button[contains(text(),'تم')]"));
        confirmDelete.click();

         */

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

        // Loop until there are no checkboxes left
        while (true) {
            // Locate all checkboxes in the datatable
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

            // Check if there are no checkboxes left
            if (checkboxes.isEmpty()) {
                System.out.println("No more checkboxes to delete.");
                break; // Exit the loop if no checkboxes are found
            }

            // Iterate through the list and click each checkbox
            for (WebElement checkbox : checkboxes) {
                if (!checkbox.isSelected()) { // Check if the checkbox is not already selected
                    checkbox.click(); // Click the checkbox
                }
            }

            // Scroll to the top of the page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");

            // Click the action list button
            WebElement actionList = driver.findElement(By.xpath("//button[@type='button' and contains(@class, 'dropdown-toggle')]"));
            actionList.click();

            // Click the delete action
            WebElement deleteAction = driver.findElement(By.xpath("//a[contains(text(),'حذف')]"));
            deleteAction.click();

            // Confirm delete
            WebElement confirmDelete = driver.findElement(By.xpath("//button[contains(text(),'موافق')]"));
            confirmDelete.click();


            // Optional: Wait for a while to observe the deletion process
            Thread.sleep(2000); // Adjust the sleep time as necessary

            // Refresh the page
            driver.navigate().refresh();

            // Optional: Wait for the page to load completely
            Thread.sleep(2000); // Adjust the sleep time as necessary
        }

    }
}
