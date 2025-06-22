package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tenants_Admin extends randomGenerator {


    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public Tenants_Admin(WebDriver driver) {
        this.driver = driver;
    }


    // Locators
    By manageContracts = By.xpath("//span[contains(text(),'Manage Contracts')]");

    By tenants = By.xpath("//li[@role='treeitem']//a[.//span[text()='Tenants']]");

    By allTab = By.xpath("//span[normalize-space()='All']");

    By viewButton = By.xpath("//tr[@role='row'][1]//a[contains(@class, 'btn-outline') and contains(@class, 'btn-primary') and contains(text(), 'View')]");

    By fileUpload = By.xpath("//input[@id='contract-tenant-view-input-file-upload']");

    By documentsTab = By.xpath("//a[normalize-space()='My Documents']");

    By confirmDeleteDocument = By.xpath("//button[normalize-space()='Delete']//button[normalize-space()='Delete']");

    By petsTab = By.xpath("//button[3]//div[1]");

    By addPetsButton = By.xpath("//button[normalize-space()='Add pet']");

    By petCategory = By.xpath("//label[@for='newPet']");

    By petList = By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/form[1]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]");
    
    By petName = By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/form[1]/div[2]/form[1]/div[1]/div[3]/div[1]/input[1]");

    By submitNewPet = By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/form[1]/div[3]/button[1]/span[2]");

    String nameOfPet;

    By petsTabTenant = By.xpath("//a[normalize-space()='My Pets']");

    // Action Methods

    public void openTenantsAndView() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(manageContracts).click();

        driver.findElement(tenants).click();

        driver.findElement(allTab).click();

        driver.findElement(viewButton).click();

    }

    public void uploadDocuments() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        Thread.sleep(500);

        driver.findElement(fileUpload).sendKeys("C:\\Users\\Electronica Care\\Yarn\\Pom\\logo-white.png");

        driver.navigate().refresh();

        Thread.sleep(500);

        driver.findElement(fileUpload).sendKeys("C:\\Users\\Electronica Care\\Yarn\\Pom\\New Microsoft Word Document.pdf");

    }

    public void checkDocumentsInTenant() {

        driver.findElement(documentsTab).click();

        // Step 1: Locate all file blocks in the grid
        List<WebElement> fileBlocks = driver.findElements(By.cssSelector(".image-block"));

        // Step 2: Iterate through each file block and extract the file name
        for (WebElement fileBlock : fileBlocks) {
            // Check if the file is an image file
            List<WebElement> imageFileNameElements = fileBlock.findElements(By.cssSelector("p.truncate"));
            if (!imageFileNameElements.isEmpty()) {
                String fileName = imageFileNameElements.get(0).getText();
                System.out.println("File Name: " + fileName);
            } else {
                // If not an image file, check for non-image files (e.g., PDFs)
                List<WebElement> nonImageFileNameElements = fileBlock.findElements(By.cssSelector("p.text-sm.text-center.break-words"));
                if (!nonImageFileNameElements.isEmpty()) {
                    String fileName = nonImageFileNameElements.get(0).getText();
                    System.out.println("File Name: " + fileName);
                }
            }
        }
    }

    public void deleteDocumentsFromAdmin() {

        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        while (true) {
            // Locate all file cards (excluding upload box)
            List<WebElement> fileCards = driver.findElements(By.cssSelector(".galleria-container .relative.group"));

            if (fileCards.isEmpty()) {
                System.out.println("No more files to delete.");
                break;
            }

            // Click on the first file's delete button
            WebElement deleteButton = fileCards.get(0).findElement(By.cssSelector(".pi.pi-trash"));
            deleteButton.click();

            // Wait for SweetAlert to appear
            WebElement swalConfirm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".swal2-actions .swal2-confirm")));
            swalConfirm.click();

            // Optional: Wait for deletion animation or update
            try {
                Thread.sleep(1000); // Adjust as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /*==========================================================*/

    public void addPetsFromAdminAndGetPetData() throws InterruptedException {

        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Random rand = new Random();

        Thread.sleep(6000);

        // Scroll to Pets Tab and click
        WebElement petsTabElement = driver.findElement(petsTab);
        js.executeScript("arguments[0].scrollIntoView(true);", petsTabElement);
        petsTabElement.click();

        // Click Add Pet Button
        wait.until(ExpectedConditions.elementToBeClickable(addPetsButton)).click();

        // Click Pet Category Dropdown Trigger
        wait.until(ExpectedConditions.elementToBeClickable(petCategory)).click();


        // Click the dropdown trigger for pet list
        WebElement dropdownTrigger = wait.until(ExpectedConditions.elementToBeClickable(petList));
        dropdownTrigger.click();

        // Wait for dropdown items to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='p-dropdown-items']")));

        // Get all available options
        List<WebElement> options = driver.findElements(By.xpath(
                "//ul[@class='p-dropdown-items']//span[@class='p-dropdown-item-label']"
        ));

        if (!options.isEmpty()) {
            int randomIndex = rand.nextInt(options.size());
            WebElement selectedOption = options.get(randomIndex);
            String selectedText = selectedOption.getText();

            // Click using JS if normal click fails
            try {
                selectedOption.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", selectedOption);
            }

            System.out.println("Randomly selected: " + selectedText);
        } else {
            System.out.println("No options found in the dropdown.");
        }

        nameOfPet = visitor.firstName;

        driver.findElement(petName).sendKeys(nameOfPet);

        driver.findElement(submitNewPet).click();

    }

    public void checkPetFromTenant() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(petsTabTenant).click();
        Thread.sleep(6000); // Wait for the page to load

        // Refresh the page
        driver.navigate().refresh();
        Thread.sleep(6000); // Wait for the page to refresh

        // Scroll down by pixel count
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(6000); // Wait for content to load after scrolling

        // Locate the last row of the pet list
        List<WebElement> petRows = driver.findElements(By.cssSelector(".grid.grid-cols-6.gap-2.items-center"));

        // Check if petRows is not empty
        if (!petRows.isEmpty()) {
            WebElement lastPetRow = petRows.get(petRows.size() - 1);

            // Extract pet name and type
            String petName = lastPetRow.findElement(By.xpath(".//div[1]")).getText(); // Pet Name
            String petType = lastPetRow.findElement(By.xpath(".//div[4]")).getText(); // Pet Type (hidden on small screens)

            // Print the results
            System.out.println("Pet Name: " + petName);
            System.out.println("Pet Type: " + petType);

            // Assert that the pet name matches
            assert petName.equals(nameOfPet) : "Pet name does not match! Expected: " + nameOfPet + ", Found: " + petName;
            System.out.println("Pet name matches successfully.");

        } else {
            System.out.println("No pets found.");
        }
    }

}

