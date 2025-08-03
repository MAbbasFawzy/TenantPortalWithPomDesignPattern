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

    public static String nameOfPet;

    public static String vehiclePlateNumber;

    public static String nameOfDependent;

    By petsTabTenant = By.xpath("//a[normalize-space()='My Pets']");

    By vehicleTab = By.xpath("//button[2]//div[1]");

    By addVehicleButton = By.xpath("//button[normalize-space()='Add Vehicle']");

    By plateNumber = By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/form[1]/div[1]/div[1]/input[1]");

    By vehicleColor = By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/form[1]/div[1]/div[2]/input[1]");

    By submitVehicle = By.xpath("//button[@aria-label='Submit']");

    By vehicleMenuItem = By.xpath("//span[normalize-space()='Vehicles']");

    By createVehicle = By.xpath("//a[@class='btn btn-primary']");

    By continueAddingVehicle = By.xpath("//button[normalize-space()='Continue']");

    By carPlate = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[2]/form[1]/div[2]/div[1]/input[1]");

    By carColor = By.xpath("");

    By submitCar = By.xpath("//button[@class='btn btn-lg btn-primary']");

    By carTabTenant = By.xpath("//a[normalize-space()='My Vehicles']");

    By dependentsTab = By.xpath("//div[@class='grid grid-cols-2 sm:grid-cols-5 gap-3']//button[1]");

    By addDependentButton = By.xpath("//button[normalize-space()='Add dependent']");

    By dependentType = By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/div[1]/form[1]/div[2]/div[2]");

    By dependentName = By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]");

    By dependentEmail = By.xpath("//div[@class='p-accordion p-component']//div[3]//div[1]//input[1]");

    By submitDependent = By.xpath("//button[normalize-space()='Submit']");

    By dependentsTabTenant = By.xpath("//a[normalize-space()='My Dependents']");

    By viewDependant = By.xpath("//a[@class='btn btn-outline btn-primary mb-1']//i[@class='fa fa-long-arrow-right icon-right']");

    By actionMenu = By.xpath("//button[@role='button']");

    By deleteDependant = By.xpath("//a[normalize-space()='Delete']");

    By confirmDelete = By.xpath("//button[normalize-space()='Delete']");

    By viewPets = By.xpath("//tbody/tr/td[7]/a[1]");

    By actionMenuForPets = By.xpath("//i[@class='fa-lg fa fa-ellipsis-v']");

    By deletePets = By.xpath("//a[normalize-space()='Delete']");

    By confirmDeletePet = By.xpath("//button[normalize-space()='Delete']");



    By vehicleActionMenu = By.xpath("//i[@class='fa-solid fa-ellipsis-vertical']");

    By deleteVehicle = By.xpath("//button[@class='dropdown-item text-danger py-2 px-3']");

    By confirmDeleteVehicle = By.xpath("//button[normalize-space()='Delete']");

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

        System.out.println("Name of pet added from the admin portal: " + nameOfPet);

        driver.findElement(submitNewPet).click();

    }

    public void checkPetFromTenant(String nameOfPet) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(petsTabTenant).click();
        Thread.sleep(6000); // Wait for the page to load

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

            System.out.println(nameOfPet);
            // Assert that the pet name matches
            assert petName.equals(nameOfPet) : "Pet name does not match! Expected: " + nameOfPet + ", Found: " + petName;
            System.out.println("Pet name matches successfully.");

        } else {
            System.out.println("No pets found.");
        }
    }

    public void deletePetsFromAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(viewPets).click();

        driver.findElement(actionMenuForPets).click();

        Thread.sleep(2000);

        driver.findElement(deletePets).click();

        Thread.sleep(2000);

        driver.findElement(confirmDeletePet).click();


    }

    /*==========================================================*/


    public void addVehicleFromAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(vehicleMenuItem).click();

        driver.findElement(createVehicle).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click on the dropdown trigger
        WebElement dropdownTrigger = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"
        )));
        dropdownTrigger.click();

        // Step 2: Wait for the dropdown items to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//ul[@class='p-dropdown-items' and @role='listbox']"
        )));

        // Step 3: Select the first item
        WebElement firstOption = driver.findElement(By.xpath(
                "//ul[@class='p-dropdown-items']//span[@class='p-dropdown-item-label'][1]"
        ));
        firstOption.click();

        WebDriverWait waitVehicleOwnerList = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click on the dropdown trigger
        WebElement dropdownTriggerVehicleOwnerList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"
        )));
        dropdownTriggerVehicleOwnerList.click();

        // Step 2: Wait for the dropdown options to appear
        waitVehicleOwnerList.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//ul[@class='p-dropdown-items' and @role='listbox']"
        )));

        // Step 3: Find and click the "Tenant" option
        WebElement tenantOption = driver.findElement(By.xpath(
                "//ul[@class='p-dropdown-items']//span[@class='p-dropdown-item-label' and normalize-space()='Tenant']"
        ));
        tenantOption.click();

        driver.findElement(continueAddingVehicle).click();

        // Wait for the dropdown to be clickable
        WebDriverWait waitTenantList = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement dropdownElement = waitTenantList.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]")
        ));
        Thread.sleep(2000);
        dropdownElement.click();

        Thread.sleep(2000);
        // Wait for the search box to appear
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[contains(@class, 'p-dropdown-filter')]")
        ));

        Thread.sleep(2000);
        // Enter the tenant name in the search box
        searchBox.sendKeys("yarn.user.tenant");
        // Wait for the dropdown items to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[contains(@class, 'p-dropdown-items')]")
        ));

        Thread.sleep(2000);
        // Get all available options
        List<WebElement> options = driver.findElements(By.xpath(
                "//ul[contains(@class, 'p-dropdown-items')]//li[contains(@class, 'p-dropdown-item')]"
        ));
        // Select the matching option
        for (WebElement option : options) {
            if (option.getAttribute("aria-label").equalsIgnoreCase("yarn.user.tenant")) {
                option.click(); // Click the matching option
                break;
            }
        }

        driver.findElement(carPlate).sendKeys(visitor.numbers);

        Thread.sleep(4000);

        // Scroll down by pixel count
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to bottom of page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");


        driver.findElement(submitCar).click();

        Thread.sleep(4000);

        // Locate the "Car Plate" label by text and get the next <div> sibling
        WebElement plateLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//h4[normalize-space()='Car Plate:']"
        )));

        // Find the following sibling div containing the actual plate number
        WebElement plateValueElement = plateLabel.findElement(By.xpath("following-sibling::div[1]"));

        if (plateValueElement != null && !plateValueElement.getText().isEmpty()) {

            System.out.println("Car Plate Number: " + plateValueElement.getText());

            vehiclePlateNumber = plateValueElement.getText();
        } else {
            System.out.println("Car Plate number not found or is empty.");
        }
    }

    public void checkVehicleFromTenant(String vehiclePlateNumber) {

        driver.findElement(carTabTenant).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        /*
        // Find the last card in the grid
        WebElement lastCard = driver.findElement(By.xpath(
                "//div[@class='card flex flex-col gap-3 yarn-shadow']"
        ));

        // Extract the plate number from the last card
        WebElement plateLabel = lastCard.findElement(By.xpath(".//label[contains(@class, 'text-sm') and text()='Plate']"));
        WebElement plateValue = plateLabel.findElement(By.xpath("./following-sibling::h4"));

        String plateNumber = plateValue.getText();
        System.out.println("Last vehicle plate number: " + plateNumber);

        // Assert that the pet name matches
        assert plateNumber.equals(vehiclePlateNumber) : "Vehicle plate number does not match! Expected: " + vehiclePlateNumber + ", Found: " + plateNumber;
        System.out.println("Car plate number matches successfully.");

         */

        // Wait for grid to be present
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'grid') and contains(@class, 'sm:grid-cols-3') and contains(@class, 'gap-4') and contains(@class, 'mb-4')]")));

        // Retry fetching cards and reading data to avoid stale reference
        String plateNumber = null;
        int maxRetries = 3;

        for (int i = 0; i < maxRetries; i++) {
            try {
                // Re-locate the grid fresh
                WebElement grid = driver.findElement(By.xpath(
                        "//div[contains(@class, 'grid') and contains(@class, 'sm:grid-cols-3') and contains(@class, 'gap-4') and contains(@class, 'mb-4')]"));

                // Re-locate all cards fresh
                List<WebElement> cards = grid.findElements(By.xpath(
                        ".//div[contains(@class, 'card') and contains(@class, 'flex') and contains(@class, 'flex-col') and contains(@class, 'gap-3') and contains(@class, 'yarn-shadow')]"));

                if (cards.isEmpty()) {
                    throw new RuntimeException("No vehicle cards found.");
                }

                // Get last card
                WebElement latestCard = cards.get(cards.size() - 1);

                // Extract plate number immediately after locating
                plateNumber = latestCard.findElement(By.xpath(".//label[text()='Plate:']/following-sibling::h4")).getText();

                break; // Success → exit loop
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element encountered, retrying... Attempt " + (i + 1));
                try {
                    Thread.sleep(1000); // brief pause before retry
                } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
            }
        }

        if (plateNumber == null) {
            throw new RuntimeException("Failed to retrieve plate number after multiple retries.");
        }

    }

    public void deleteVehicleFromAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));


        driver.findElement(vehicleActionMenu).click();

        Thread.sleep(2000);

        driver.findElement(deleteVehicle).click();

        Thread.sleep(2000);

        driver.findElement(confirmDeleteVehicle).click();

        Thread.sleep(2000);


    }

    /*==========================================================*/

    public void addDependentFromAdminAndGetData() throws InterruptedException {

        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Random rand = new Random();

        Thread.sleep(6000);

        // Scroll to Pets Tab and click
        WebElement dependentsTabElement = driver.findElement(dependentsTab);
        js.executeScript("arguments[0].scrollIntoView(true);", dependentsTabElement);
        dependentsTabElement.click();

        // Click Add Dependent Button
        wait.until(ExpectedConditions.elementToBeClickable(addDependentButton)).click();

        WebDriverWait waitRelationList = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Step 1: Locate and click the dropdown trigger icon
        WebElement dropdownTrigger = waitRelationList.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html[1]/body[1]/div[10]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]"
        )));
        dropdownTrigger.click();

        // Step 2: Wait for dropdown options to appear
        waitRelationList.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//ul[@class='p-dropdown-items' and @role='listbox']"
        )));

        // Step 3: Get all visible options
        List<WebElement> options = driver.findElements(By.xpath(
                "//ul[@class='p-dropdown-items']//span[@class='p-dropdown-item-label']"
        ));

        if (!options.isEmpty()) {
            int randomIndex = rand.nextInt(options.size());
            WebElement selectedOption = options.get(randomIndex);
            String selectedText = selectedOption.getText();

            // Click the randomly selected option
            selectedOption.click();
            System.out.println("Randomly selected: " + selectedText);
        } else {
            System.out.println("No options found in the dropdown.");
        }

        driver.findElement(dependentType).click();

        Thread.sleep(6000);

        nameOfDependent = visitor.firstName;

        driver.findElement(dependentName).sendKeys(nameOfDependent);

        driver.findElement(dependentEmail).sendKeys(visitor.firstName + "@gmail.com");


        WebDriverWait waitGenderList = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Step 1: Click on the gender dropdown trigger icon
        WebElement dropdownTriggerGender = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html[1]/body[1]/div[10]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[12]/div[1]" // Adjust this XPath as needed
        )));
        dropdownTriggerGender.click();
        // Step 2: Wait for gender dropdown options to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//ul[@class='p-dropdown-items' and @role='listbox']"
        )));
        // Step 3: Get all visible gender options
        List<WebElement> optionsGender = driver.findElements(By.xpath(
                "//ul[@class='p-dropdown-items']//span[@class='p-dropdown-item-label']"
        ));
        if (!optionsGender.isEmpty()) {
            int randomIndex = rand.nextInt(optionsGender.size()); // Use optionsGender.size() here
            WebElement selectedOptionGender = optionsGender.get(randomIndex);
            String selectedTextGender = selectedOptionGender.getText();
            // Click the randomly selected gender option
            selectedOptionGender.click();
            System.out.println("Randomly selected gender: " + selectedTextGender);
        } else {
            System.out.println("No gender options found.");
        }


        driver.findElement(submitDependent).click();
        Thread.sleep(2000);
    }

    public void checkDependentFromTenant(String nameOfDependent) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(dependentsTabTenant).click();

        Thread.sleep(2000); // Wait for scrolling to complete
        // Locate all rows in the dependents grid
        List<WebElement> dependentRows = driver.findElements(By.xpath("//div[contains(@class, 'grid grid-cols-5 gap-2 items-center')]"));
        // Check if there are any rows
        if (!dependentRows.isEmpty()) {
            // Get the last row
            WebElement lastDependentRow = dependentRows.get(dependentRows.size() - 1);
            // Extract the dependent name (first column)
            String dependentName = lastDependentRow.findElement(By.xpath(".//div[1]")).getText(); // Adjust the index if necessary

            // Print the dependent name

            System.out.println("Dependent Name: " + dependentName);
            assert dependentName.equals(nameOfDependent) : "Dependent name does not match! Expected: " + nameOfDependent + ", Found: " + dependentName;
            System.out.println("Dependent name matches successfully.");
        } else {
            System.out.println("No dependents found.");
        }

    }

    public void deleteDependantFromAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(viewDependant).click();

        Thread.sleep(4000);

        driver.findElement(actionMenu).click();

        driver.findElement(deleteDependant).click();

        driver.findElement(confirmDelete).click();

    }
}

