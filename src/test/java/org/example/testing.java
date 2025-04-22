package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class testing {


    private static final Logger log = LoggerFactory.getLogger(testing.class);
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.navigate().to("http://uat-resorsa.faltechnology.solutions/");
    }

    @Test(priority = 0)
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

    @Test(priority = 1)
    public void openAssetsPage() throws InterruptedException {
        // Navigate to the Assets page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        WebElement assetsPageLink = driver.findElement(By.xpath("//a[@href='#/org/assets']//span[@class='menu-title text-truncate ng-star-inserted'][contains(text(),'الأصول')]"));
        assetsPageLink.click();

        Thread.sleep(2000); // Wait for the page to load

        /*
        // Open the hierarchy dropdown
        WebElement hierarchyList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/fieldset[1]/ng-select[1]/div[1]/div[1]/div[2]/input[1]"));
        hierarchyList.click();

        // Wait for the dropdown options to appear
        Thread.sleep(2000);

        // Select the first option (optional, can be removed if not needed)
        List<WebElement> options = driver.findElements(By.cssSelector(".ng-option"));
        if (!options.isEmpty()) {
            options.get(0).click();
        }

        Thread.sleep(2000);



        // Click the search button
        WebElement searchButton = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/fieldset[1]/button[1]/span[1]"));
        searchButton.click();



        // Adjust the number of inputs per page to 100
        WebElement inputsCountList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[2]/div[1]/div[2]/div[1]/label[1]/select[1]"));
        inputsCountList.click();

        WebElement selectElement = driver.findElement(By.cssSelector(".form-control"));
        Select select = new Select(selectElement);
        select.selectByValue("100");

         */
    }

    @Test(priority = 2)
    public void deleteAssetsForSpecificRooms() throws InterruptedException {
        System.out.println("Delete process started");

        // Define the array of room names to process
        String[] roomsToProcess = {

                "ممر الخدمات الطابق التاسع عشر",
        };

        // Wait for the dropdown to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        // Iterate through each room in the array
        for (String roomName : roomsToProcess) {
            System.out.println("Processing room: " + roomName);

            Thread.sleep(6000);
            // Locate the hierarchy dropdown (re-fetch it after every refresh)
            WebElement hierarchyList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/fieldset[1]/ng-select[1]/div[1]/div[1]/div[2]/input[1]"));

            // Open the hierarchy dropdown
            hierarchyList.click();

            // Wait for the dropdown options to appear
            Thread.sleep(2000);

            // Fetch all room options from the dropdown
            List<WebElement> roomOptions = driver.findElements(By.cssSelector(".ng-option"));

            // Find and select the current room from the dropdown
            boolean roomFound = false;
            for (WebElement roomOption : roomOptions) {
                String optionText = roomOption.getText().trim();
                if (optionText.equals(roomName)) {
                    roomOption.click();
                    roomFound = true;
                    break;
                }
            }

            if (!roomFound) {
                System.out.println("Room not found: " + roomName);
                continue; // Skip to the next room if the current room is not found
            }

            // Click the search button to load assets for the selected room
            WebElement searchButton = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/fieldset[1]/button[1]/span[1]"));
            searchButton.click();

            // Wait for the results to load
            Thread.sleep(2000);

            // Adjust the number of inputs per page to 100
            WebElement inputsCountList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[2]/div[1]/div[2]/div[1]/label[1]/select[1]"));
            inputsCountList.click();

            WebElement selectElement = driver.findElement(By.cssSelector(".form-control"));
            Select select = new Select(selectElement);
            select.selectByValue("100");
            Thread.sleep(6000);

            // Delete all assets for the current room
            deleteAssetsForCurrentRoom();

            Thread.sleep(10000);
            // Refresh the page to reset the state
            driver.navigate().refresh();

            // Wait for the page to reload
            Thread.sleep(6000);

            // Open the hierarchy dropdown
            hierarchyList.click();
        }

        System.out.println("All specified rooms processed.");
    }


    private void deleteAssetsForCurrentRoom() throws InterruptedException {
        System.out.println("Deleting assets for the current room...");

        while (true) {
            // Locate all checkboxes in the datatable
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

            // Check if there are no checkboxes left
            if (checkboxes.isEmpty()) {
                System.out.println("No more assets to delete in the current room.");
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
            Thread.sleep(6000); // Adjust the sleep time as necessary

            // Refresh the page to reflect the changes
            driver.navigate().refresh();

            // Optional: Wait for the page to load completely
            Thread.sleep(6000); // Adjust the sleep time as necessary
        }

    }



    /*
    @Test(priority = 1)
    public void openAssetsPage() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        WebElement assetsPageLink = driver.findElement(By.xpath("//a[@href='#/org/assets']//span[@class='menu-title text-truncate ng-star-inserted'][contains(text(),'الأصول')]"));
        assetsPageLink.click();

        Thread.sleep(2000);

        WebElement hierarchyList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/fieldset[1]/ng-select[1]/div[1]/div[1]/div[2]/input[1]"));
        hierarchyList.click();

        // Wait for the dropdown list to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Thread.sleep(2000);
        // Select the first element from the list
        List<WebElement> options = driver.findElements(By.cssSelector(".ng-option"));
        options.get(0).click();

        Thread.sleep(2000);
        WebElement searchButton = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/fieldset[1]/button[1]/span[1]"));
        searchButton.click();

        Thread.sleep(2000);
        WebElement inputsCountList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[2]/div[1]/div[2]/div[1]/label[1]/select[1]"));
        inputsCountList.click();

        Thread.sleep(1000);
        // Click on the select element
        WebElement selectElement = driver.findElement(By.cssSelector(".form-control"));
        selectElement.click();

        // Wait for the options to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Select the option with value 100
        Select select = new Select(selectElement);
        select.selectByValue("100");
    }


    @Test(priority = 2)
    public void deleteAssetsForSpecificRooms() throws InterruptedException {
        System.out.println("Delete process started");

        // Wait for the dropdown to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        // Locate the hierarchy dropdown
        WebElement hierarchyList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/fieldset[1]/ng-select[1]/div[1]/div[1]/div[2]/input[1]"));
        hierarchyList.click();

        // Wait for the dropdown options to appear
        Thread.sleep(2000);

        // Fetch all room options from the dropdown
        List<WebElement> roomOptions = driver.findElements(By.cssSelector(".ng-option"));

        // Filter room options to include only "الغرفة 901" to "مكتب خدمة الغرف الطابق التاسع"
        List<WebElement> filteredRooms = new ArrayList<>();
        boolean startRange = false;

        for (WebElement room : roomOptions) {
            String roomName = room.getText().trim();

            // Start adding rooms when we reach "الغرفة 901"
            if (roomName.equals("الغرفة 901")) {
                startRange = true;
            }

            // Add rooms to the filtered list if within the range
            if (startRange) {
                filteredRooms.add(room);
            }

            // Stop adding rooms when we reach "مكتب خدمة الغرف الطابق التاسع"
            if (roomName.equals("مكتب خدمة الغرف الطابق التاسع")) {
                break;
            }
        }

        // Iterate through each filtered room
        for (WebElement currentRoom : filteredRooms) {
            String roomName = currentRoom.getText().trim();
            System.out.println("Processing room: " + roomName);

            // Select the current room
            currentRoom.click();

            // Click the search button to load assets for the selected room
            WebElement searchButton = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/fieldset[1]/button[1]/span[1]"));
            searchButton.click();

            // Wait for the results to load
            Thread.sleep(2000);

            // Delete all assets for the current room
            deleteAssetsForCurrentRoom();

            // Refresh the page to reset the state
            driver.navigate().refresh();

            // Wait for the page to reload
            Thread.sleep(2000);

            // Re-open the hierarchy dropdown for the next room
            hierarchyList = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/vertical-layout[1]/div[1]/content[1]/div[1]/app-assets[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/fieldset[1]/ng-select[1]/div[1]/div[1]/div[2]/input[1]"));
            hierarchyList.click();

            // Wait for the dropdown options to appear again
            Thread.sleep(2000);
        }

        System.out.println("All specified rooms processed.");
    }

    private void deleteAssetsForCurrentRoom() throws InterruptedException {
        System.out.println("Deleting assets for the current room...");

        while (true) {
            // Locate all checkboxes in the datatable
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

            // Check if there are no checkboxes left
            if (checkboxes.isEmpty()) {
                System.out.println("No more assets to delete in the current room.");
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

            // Refresh the page to reflect the changes
            driver.navigate().refresh();

            // Optional: Wait for the page to load completely
            Thread.sleep(2000); // Adjust the sleep time as necessary
        }



    /*
    private void deleteAssetsForCurrentRoom() throws InterruptedException {
        System.out.println("Deleting assets for the current room...");

        while (true) {
            // Locate all checkboxes in the datatable
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

            // Check if there are no checkboxes left
            if (checkboxes.isEmpty()) {
                System.out.println("No more assets to delete in the current room.");
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

            // Refresh the page to reflect the changes
            driver.navigate().refresh();

            // Optional: Wait for the page to load completely
            Thread.sleep(2000); // Adjust the sleep time as necessary
        }
     */


    /*
    @Test (priority = 2)

    public void deleteAsset() throws InterruptedException {

        System.out.println("Delete started");

        /* deleting the whole list
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

        /*
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

         */
    }
