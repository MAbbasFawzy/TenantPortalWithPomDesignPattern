package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Tenants_Admin {


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
}
