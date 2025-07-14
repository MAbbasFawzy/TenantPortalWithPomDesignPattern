package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class Visitor extends randomGenerator {


    public WebDriver driver;

    // Constructor
    public Visitor(WebDriver driver) {

        this.driver = driver;
    }

    // Locators

    By addVisitor = By.xpath("//button[@type='button']");

    By visitType = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]");

    By visitTypeOption = By.xpath("//ul[@class='p-dropdown-items']/li");

    By entryTypeSingle = By.xpath("//input[@id='single']");

    By entryTypeMultiple = By.xpath("//input[@id='multiple']");

    By visitEndDate = By.xpath("//div[@class='vdatetime-calendar__month']");


    By visitDate = By.xpath("//div[@class='vdatetime-calendar__month']");

    By visitorFirstName = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]");

    By visitorSecondName = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]");

    By documentType = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]");

    By documentTypeOption = By.xpath("//ul[contains(@class, 'p-dropdown-items')]/li");

    By documentNumber = By.xpath("//body//div//div[@data-pc-section='content']//div//div//div[4]//input[1]");

    By nationalityList = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]");

    By nationalityListOption = By.xpath("//ul[contains(@class, 'p-dropdown-items')]/li");

    By genderList = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[6]/div[1]");

    By genderListOption = By.xpath("//ul[contains(@class, 'p-dropdown-items')]/li");

    By submitVisitor = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[4]/button[2]");

    By visitorStatus = By.xpath("//span[contains(@class, 'py-1 px-2') and text()='Pending']");

    By withVehicle = By.xpath("//input[@id='visitor-0-with-vehicle']");

    By addOneMoreVisitor = By.xpath("//button[normalize-space()='Add one more visitor']");

    By extraVisitorFirstName = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]");

    By extraVisitorSecondName = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]");

    By documentTypeExtraVisitor = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]");

    By documentNumberExtraVisitor = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[4]/input[1]");

    By nationalityListExtraVisitor = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]");

    By genderListExtraVisitor = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[6]/div[1]");

    By vehicleExtraVisitor = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/span[2]");

    By visitorsMenuItem = By.xpath("/html[1]/body[1]/div[2]/div[1]/nav[1]/div[1]/div[3]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]");

    By visitorIDColumn = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/thead[1]/tr[1]/th[4]/div[1]");

    By viewVisitor = By.xpath("//tbody/tr[1]/td[36]/a[1]");

    By actionMenu = By.xpath("//button[@id='visitorStatusDropdownMenuButton']");

    By expiredStatus = By.xpath("//button[normalize-space()='Expired']");

    By unwelcomeStatus = By.xpath("//button[normalize-space()='Unwelcome']");

    By confirmExpiredStatus = By.xpath("//button[@type='button'][normalize-space()='Expired']");

    By confirmUnwelcomeStatus = By.xpath("//button[@type='button'][normalize-space()='Unwelcome']");

    By resubmit = By.xpath("//button[normalize-space()='Resubmit']");

    By visitStartDate = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/input[1]");

    By continueButton = By.xpath("//div[@class='vdatetime-popup__actions__button vdatetime-popup__actions__button--confirm']");

    By submitExpiredVisitor = By.xpath("//button[normalize-space()='Submit']");

    By unwelcomeAlert = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]");

    public static String documentOption;

    public static String docNumber;




    // Action Methods

    public void addVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

        Thread.sleep(6000);
        driver.findElement(addVisitor).click();


        Thread.sleep(6000);
        driver.findElement(visitType).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        Thread.sleep(500);

        // Wait for the dropdown options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(visitTypeOption));

        // Generate a random index to select an option
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        // Scroll to the selected option
        WebElement selectedOption = options.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random option
        selectedOption.click();

        driver.findElement(entryTypeSingle).click();

        Thread.sleep(6000);
        driver.findElement(visitorFirstName).sendKeys(visitor.firstName);

        Thread.sleep(6000);
        driver.findElement(visitorSecondName).sendKeys(visitor.lastName);



        /*---------------------------------------------------------*/

        Thread.sleep(6000);
        driver.findElement(documentType).click();
        // Wait for the dropdown options to be visible
        List<WebElement> optionsDocument = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(documentTypeOption));
        Thread.sleep(500);
        // Generate a random index for document type
        int randomIndexDocument = random.nextInt(optionsDocument.size());
        // Scroll to the selected document option
        WebElement selectedOptionDocument = optionsDocument.get(randomIndexDocument);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionDocument);
        Thread.sleep(500); // Optional: wait for the scroll to complete
        // Save the selected document type in a variable
        documentOption = selectedOptionDocument.getText().trim();
        // Select the random document option
        selectedOptionDocument.click();

        docNumber = visitor.numbers;
        driver.findElement(documentNumber).sendKeys(docNumber);

        
        /*---------------------------------------------------------*/



        Thread.sleep(6000);
        // Scroll to the dropdown element
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(nationalityList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(nationalityList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        // Wait for the dropdown options to be visible
        List<WebElement> countryOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nationalityListOption));

        // Generate a random index to select an option
        Random randomCountry = new Random();
        int randomIndexCountry = random.nextInt(countryOptions.size());

        // Select the random option
        WebElement selectedOptionCountry = countryOptions.get(randomIndexCountry);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionCountry); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionCountry.click();

        Thread.sleep(6000);
        // Scroll to the dropdown element
        WebElement dropdownGender = wait.until(ExpectedConditions.elementToBeClickable(genderList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownGender);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(genderList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGender);

        // Wait for the dropdown options to be visible
        List<WebElement> genderOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(genderListOption));

        // Generate a random index to select an option
        Random randomGender = new Random();
        int randomIndexGender = randomGender.nextInt(genderOptions.size());

        // Select the random option
        WebElement selectedOptionGender = genderOptions.get(randomIndexGender); // Fixed: Use genderOptions here
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionGender); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionGender.click(); // Click the selected gender option

        driver.findElement(submitVisitor).click();

        driver.findElement(visitorStatus).getText();

        Assert.assertEquals("Pending", driver.findElement(visitorStatus).getText());

    }

    public void addNewVisitorsGroup() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();


        driver.findElement(addVisitor).click();
        Thread.sleep(2000);
        driver.findElement(visitType).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        Thread.sleep(500);

        // Wait for the dropdown options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(visitTypeOption));

        // Generate a random index to select an option
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        // Scroll to the selected option
        WebElement selectedOption = options.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random option
        selectedOption.click();

        driver.findElement(entryTypeSingle).click();

        driver.findElement(visitorFirstName).sendKeys(visitor.firstName);
        driver.findElement(visitorSecondName).sendKeys(visitor.lastName);

        /*---------------------------------------------------------*/

        driver.findElement(documentType).click();
        // Wait for the dropdown options to be visible
        List<WebElement> optionsDocument = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(documentTypeOption));

        Thread.sleep(500);

        // Generate a random index for document type
        int randomIndexDocument = random.nextInt(optionsDocument.size());

        // Scroll to the selected document option
        WebElement selectedOptionDocument = optionsDocument.get(randomIndexDocument);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionDocument);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random document option
        selectedOptionDocument.click();

        driver.findElement(documentNumber).sendKeys(visitor.numbers);

        /*---------------------------------------------------------*/

        Thread.sleep(500);
        // Scroll to the dropdown element
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(nationalityList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(nationalityList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        // Wait for the dropdown options to be visible
        List<WebElement> countryOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nationalityListOption));

        // Generate a random index to select an option
        Random randomCountry = new Random();
        int randomIndexCountry = random.nextInt(countryOptions.size());

        // Select the random option
        WebElement selectedOptionCountry = countryOptions.get(randomIndexCountry);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionCountry); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionCountry.click();

        /*---------------------------------------------------------*/

        Thread.sleep(500);
        // Scroll to the dropdown element
        WebElement dropdownGender = wait.until(ExpectedConditions.elementToBeClickable(genderList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownGender);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(genderList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGender);

        // Wait for the dropdown options to be visible
        List<WebElement> genderOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(genderListOption));

        // Generate a random index to select an option
        Random randomGender = new Random();
        int randomIndexGender = randomGender.nextInt(genderOptions.size());

        // Select the random option
        WebElement selectedOptionGender = genderOptions.get(randomIndexGender); // Fixed: Use genderOptions here
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionGender); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionGender.click(); // Click the selected gender option

        /*---------------------------------------------------------*/

        driver.findElement(withVehicle).click();

        // Click on the dropdown to open it
        WebElement dropdownVehicleType = wait.until(ExpectedConditions.elementToBeClickable(By.id("car-plate-type")));
        dropdownVehicleType.click();

        // Select the "Saudi" option from the dropdown
        WebElement saudiOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("car-plate-type_0")));
        saudiOption.click();

        // Enter numbers in the first input field
        WebElement numberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Ex: 1234']")));
        numberInput.sendKeys("1234");

        // Enter letters in the second input field
        WebElement letterInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Ex: أ ب ج']")));
        letterInput.sendKeys("أ ب ج");

        /*---------------------------------------------------------*/

        driver.findElement(addOneMoreVisitor).click();

        driver.findElement(extraVisitorFirstName).sendKeys(visitor.firstName);

        driver.findElement(extraVisitorSecondName).sendKeys(visitor.lastName);

        /*---------------------------------------------------------*/

        // Document type for extra visitor
        driver.findElement(documentTypeExtraVisitor).click();
        List<WebElement> optionsDocumentExtraVisitor = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(documentTypeOption));
        Thread.sleep(500);

        int randomIndexDocumentExtraVisitor = random.nextInt(optionsDocumentExtraVisitor.size());
        WebElement selectedOptionDocumentExtraVisitor = optionsDocumentExtraVisitor.get(randomIndexDocumentExtraVisitor);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionDocumentExtraVisitor);
        Thread.sleep(500);
        selectedOptionDocumentExtraVisitor.click(); // Re-fetch the element if needed

        driver.findElement(documentNumberExtraVisitor).sendKeys(visitor.numbers);

        /*---------------------------------------------------------*/


        // Nationality for extra visitor
        WebElement dropdownNationalityExtra = wait.until(ExpectedConditions.elementToBeClickable(nationalityListExtraVisitor));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownNationalityExtra);
        wait.until(ExpectedConditions.elementToBeClickable(nationalityListExtraVisitor));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownNationalityExtra);
        List<WebElement> countryOptionsExtraVisitor = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nationalityListOption));
        int randomIndexCountryExtraVisitor = random.nextInt(countryOptionsExtraVisitor.size());
        WebElement selectedOptionCountryExtraVisitor = countryOptionsExtraVisitor.get(randomIndexCountryExtraVisitor);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionCountryExtraVisitor);
        Thread.sleep(500);
        selectedOptionCountryExtraVisitor.click();

        /*---------------------------------------------------------*/

        // Gender for extra visitor
        WebElement dropdownGenderExtra = wait.until(ExpectedConditions.elementToBeClickable(genderListExtraVisitor));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownGenderExtra);
        wait.until(ExpectedConditions.elementToBeClickable(genderListExtraVisitor));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGenderExtra);
        List<WebElement> genderOptionsExtraVisitor = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(genderListOption));
        int randomIndexGenderExtraVisitor = random.nextInt(genderOptionsExtraVisitor.size());
        WebElement selectedOptionGenderExtraVisitor = genderOptionsExtraVisitor.get(randomIndexGenderExtraVisitor);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionGenderExtraVisitor);
        Thread.sleep(500);
        selectedOptionGenderExtraVisitor.click();

        driver.findElement(vehicleExtraVisitor).click();

        // Click on the dropdown to open it
        WebElement dropdownVehicleTypeExtraVisitor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]")));
        dropdownVehicleTypeExtraVisitor.click();

        // Select the "Saudi" option from the dropdown
        WebElement nonSaudiOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[4]/div[1]/ul[1]/li[2]")));
        nonSaudiOption.click();

        WebElement nonSaudiPlateNumber = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Ex: 1 A']")));
        nonSaudiPlateNumber.sendKeys(visitor.numbers);


        driver.findElement(submitVisitor).click();

    }

    /*
    public void addVisitorExistingValidResubmit() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();


        driver.findElement(addVisitor).click();
        Thread.sleep(1000);
        driver.findElement(visitType).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        Thread.sleep(500);

        // Wait for the dropdown options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(visitTypeOption));

        // Generate a random index to select an option
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        // Scroll to the selected option
        WebElement selectedOption = options.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random option
        selectedOption.click();

        driver.findElement(entryTypeMultiple).click();

        driver.findElement(visitorFirstName).sendKeys(visitor.firstName);
        driver.findElement(visitorSecondName).sendKeys(visitor.lastName);

        driver.findElement(documentType).click();
        // Wait for the dropdown options to be visible
        List<WebElement> optionsDocument = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(documentTypeOption));

        Thread.sleep(500);

        // Generate a random index for document type
        int randomIndexDocument = random.nextInt(optionsDocument.size());

        // Scroll to the selected document option
        WebElement selectedOptionDocument = optionsDocument.get(randomIndexDocument);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionDocument);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random document option
        selectedOptionDocument.click();

        driver.findElement(documentNumber).sendKeys(visitor.numbers);

        Thread.sleep(500);
        // Scroll to the dropdown element
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(nationalityList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(nationalityList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        // Wait for the dropdown options to be visible
        List<WebElement> countryOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nationalityListOption));

        // Generate a random index to select an option
        Random randomCountry = new Random();
        int randomIndexCountry = random.nextInt(countryOptions.size());

        // Select the random option
        WebElement selectedOptionCountry = countryOptions.get(randomIndexCountry);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionCountry); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionCountry.click();

        Thread.sleep(500);
        // Scroll to the dropdown element
        WebElement dropdownGender = wait.until(ExpectedConditions.elementToBeClickable(genderList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownGender);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(genderList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGender);

        // Wait for the dropdown options to be visible
        List<WebElement> genderOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(genderListOption));

        // Generate a random index to select an option
        Random randomGender = new Random();
        int randomIndexGender = randomGender.nextInt(genderOptions.size());

        // Select the random option
        WebElement selectedOptionGender = genderOptions.get(randomIndexGender); // Fixed: Use genderOptions here
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionGender); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionGender.click(); // Click the selected gender option

        driver.findElement(submitVisitor).click();

        driver.findElement(visitorStatus).getText();

        Assert.assertEquals("Pending", driver.findElement(visitorStatus).getText());
    }

     */

    public void addVisitorWithoutResubmission() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();


        driver.findElement(addVisitor).click();
        Thread.sleep(1000);
        driver.findElement(visitType).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        Thread.sleep(500);

        // Wait for the dropdown options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(visitTypeOption));

        // Generate a random index to select an option
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        // Scroll to the selected option
        WebElement selectedOption = options.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random option
        selectedOption.click();

        driver.findElement(entryTypeSingle).click();

        driver.findElement(visitorFirstName).sendKeys(visitor.firstName);
        driver.findElement(visitorSecondName).sendKeys(visitor.lastName);

        /*---------------------------------------------------------*/

        // Use the stored documentOption and docNumber variables
        driver.findElement(documentType).click();

        // Wait for the dropdown options to be visible
        List<WebElement> optionsDocument = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(documentTypeOption));
        Thread.sleep(500);

        // Find the option that matches the stored documentOption
        WebElement selectedOptionDocument = null;
        for (WebElement option : optionsDocument) {
            if (option.getText().trim().equals(documentOption)) {
                selectedOptionDocument = option;
                break;
            }
        }

        // If the matching option is found, select it
        if (selectedOptionDocument != null) {
            // Scroll to the selected document option
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionDocument);
            Thread.sleep(500); // Optional: wait for the scroll to complete

            // Select the document option
            selectedOptionDocument.click();
        } else {
            throw new RuntimeException("Document type '" + documentOption + "' not found in the dropdown.");
        }

        // Use the stored docNumber variable
        driver.findElement(documentNumber).sendKeys(docNumber);

        /*---------------------------------------------------------*/

        /*

        Thread.sleep(500);
        // Scroll to the dropdown element
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(nationalityList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(nationalityList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        // Wait for the dropdown options to be visible
        List<WebElement> countryOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nationalityListOption));

        // Generate a random index to select an option
        Random randomCountry = new Random();
        int randomIndexCountry = random.nextInt(countryOptions.size());

        // Select the random option
        WebElement selectedOptionCountry = countryOptions.get(randomIndexCountry);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionCountry); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionCountry.click();

        Thread.sleep(500);
        // Scroll to the dropdown element
        WebElement dropdownGender = wait.until(ExpectedConditions.elementToBeClickable(genderList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownGender);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(genderList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGender);

        // Wait for the dropdown options to be visible
        List<WebElement> genderOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(genderListOption));

        // Generate a random index to select an option
        Random randomGender = new Random();
        int randomIndexGender = randomGender.nextInt(genderOptions.size());

        // Select the random option
        WebElement selectedOptionGender = genderOptions.get(randomIndexGender); // Fixed: Use genderOptions here
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionGender); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionGender.click(); // Click the selected gender option

         */

        /*
        driver.findElement(submitVisitor).click();

        driver.findElement(visitorStatus).getText();

        Assert.assertEquals("Pending", driver.findElement(visitorStatus).getText());

         */


    }

    public void openVisitorsAndExpireVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(visitorsMenuItem).click();

        Thread.sleep(2000);
        driver.findElement(visitorIDColumn).click();

        Thread.sleep(2000);
        driver.findElement(visitorIDColumn).click();

        Thread.sleep(2000);
        driver.findElement(viewVisitor).click();

        driver.findElement(actionMenu).click();

        driver.findElement(expiredStatus).click();

        driver.findElement(confirmExpiredStatus).click();

    }

    public void openVisitorsAndUnwelcomeVisitor() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(visitorsMenuItem).click();

        Thread.sleep(2000);
        driver.findElement(visitorIDColumn).click();

        Thread.sleep(2000);
        driver.findElement(visitorIDColumn).click();

        Thread.sleep(2000);
        driver.findElement(viewVisitor).click();

        driver.findElement(actionMenu).click();

        driver.findElement(unwelcomeStatus).click();

        driver.findElement(confirmUnwelcomeStatus).click();

    }

    public void resubmitVisitor(String documentOption, String docNumber) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

        Thread.sleep(6000);
        driver.findElement(addVisitor).click();


        Thread.sleep(6000);
        driver.findElement(visitType).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        Thread.sleep(500);

        // Wait for the dropdown options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(visitTypeOption));

        // Generate a random index to select an option
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        // Scroll to the selected option
        WebElement selectedOption = options.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random option
        selectedOption.click();

        driver.findElement(entryTypeSingle).click();

        Thread.sleep(6000);
        driver.findElement(visitorFirstName).sendKeys(visitor.firstName);

        Thread.sleep(6000);
        driver.findElement(visitorSecondName).sendKeys(visitor.lastName);

        /*---------------------------------------------------------*/

        Thread.sleep(6000);
        driver.findElement(documentType).click();

        // Wait for dropdown options to be visible
        List<WebElement> optionsDocument = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(documentTypeOption));

        // Find the option that matches the saved documentOption
        WebElement selectedDocOption = null;
        for (WebElement option : optionsDocument) {
            if (option.getText().trim().equals(documentOption)) {
                selectedDocOption = option;
                break;
            }
        }

        if (selectedDocOption != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedDocOption);
            Thread.sleep(500); // Optional: wait after scrolling
            selectedDocOption.click();
        } else {
            throw new RuntimeException("Saved document type not found in dropdown: " + documentOption);
        }

        // Now enter the previously used document number
        driver.findElement(documentNumber).sendKeys(docNumber);

        // Press TAB to move focus away
        driver.findElement(documentNumber).sendKeys(Keys.TAB);

        Thread.sleep(6000);
        driver.findElement(resubmit).click();

        Thread.sleep(6000);
        driver.findElement(visitStartDate).click();

        Thread.sleep(3000);
        driver.findElement(continueButton).click();

        Thread.sleep(3000);
        driver.findElement(continueButton).click();

        Thread.sleep(3000);
        driver.findElement(submitExpiredVisitor).click();


    }

    public void enterUnwelcomeVisitor(String documentOption, String docNumber) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

        Thread.sleep(6000);
        driver.findElement(addVisitor).click();


        Thread.sleep(6000);
        driver.findElement(visitType).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        Thread.sleep(500);

        // Wait for the dropdown options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(visitTypeOption));

        // Generate a random index to select an option
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        // Scroll to the selected option
        WebElement selectedOption = options.get(randomIndex);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOption);
        Thread.sleep(500); // Optional: wait for the scroll to complete

        // Select the random option
        selectedOption.click();

        driver.findElement(entryTypeSingle).click();

        Thread.sleep(6000);
        driver.findElement(visitorFirstName).sendKeys(visitor.firstName);

        Thread.sleep(6000);
        driver.findElement(visitorSecondName).sendKeys(visitor.lastName);

        /*---------------------------------------------------------*/

        Thread.sleep(6000);
        driver.findElement(documentType).click();

        // Wait for dropdown options to be visible
        List<WebElement> optionsDocument = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(documentTypeOption));

        // Find the option that matches the saved documentOption
        WebElement selectedDocOption = null;
        for (WebElement option : optionsDocument) {
            if (option.getText().trim().equals(documentOption)) {
                selectedDocOption = option;
                break;
            }
        }

        if (selectedDocOption != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedDocOption);
            Thread.sleep(500); // Optional: wait after scrolling
            selectedDocOption.click();
        } else {
            throw new RuntimeException("Saved document type not found in dropdown: " + documentOption);
        }

        // Now enter the previously used document number
        driver.findElement(documentNumber).sendKeys(docNumber);

        // Press TAB to move focus away
        driver.findElement(documentNumber).sendKeys(Keys.TAB);


        Thread.sleep(6000);
        // Scroll to the dropdown element
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(nationalityList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(nationalityList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

        // Wait for the dropdown options to be visible
        List<WebElement> countryOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nationalityListOption));

        // Generate a random index to select an option
        Random randomCountry = new Random();
        int randomIndexCountry = random.nextInt(countryOptions.size());

        // Select the random option
        WebElement selectedOptionCountry = countryOptions.get(randomIndexCountry);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionCountry); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionCountry.click();

        Thread.sleep(6000);
        // Scroll to the dropdown element
        WebElement dropdownGender = wait.until(ExpectedConditions.elementToBeClickable(genderList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownGender);

        // Wait for the dropdown to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(genderList));

        // Click the dropdown using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownGender);

        // Wait for the dropdown options to be visible
        List<WebElement> genderOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(genderListOption));

        // Generate a random index to select an option
        Random randomGender = new Random();
        int randomIndexGender = randomGender.nextInt(genderOptions.size());

        // Select the random option
        WebElement selectedOptionGender = genderOptions.get(randomIndexGender); // Fixed: Use genderOptions here
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedOptionGender); // Scroll to the selected option
        Thread.sleep(500); // Optional: wait for the scroll to complete
        selectedOptionGender.click(); // Click the selected gender option

        driver.findElement(submitVisitor).click();
    }

    public void checkUnwelcomeVisitorFromAdmin() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        driver.navigate().refresh();

        driver.findElement(visitorsMenuItem).click();

        /*
        Thread.sleep(2000);
        driver.findElement(visitorIDColumn).click();

        Thread.sleep(2000);
        driver.findElement(visitorIDColumn).click();

         */

        Thread.sleep(2000);
        driver.findElement(viewVisitor).click();

        Thread.sleep(2000);
        driver.findElement(unwelcomeAlert).getText();
    }
}
