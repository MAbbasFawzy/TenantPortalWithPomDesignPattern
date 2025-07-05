package org.example.PageObjects;

import org.example.randomGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Invoices_Admin {

    public WebDriver driver;

    randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

    // Constructor
    public Invoices_Admin(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By invoicesMenuItem = By.xpath("//a[@href='/invoices']");

    By addInvoiceButton = By.xpath("//button[@aria-label='Create an invoice']");
    
    By date = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[1]/div[7]/div[2]/div[1]/div[1]");

    By amount = By.xpath("//input[@placeholder='Amount']");

    By submitInvoice = By.xpath("//button[@aria-label='Submit']");

    By mainMenu = By.xpath("//a[@class='dropdown-toggle full']");

    By customers = By.xpath("//button[@title='Customers']//span[contains(text(),'Customers')]");

    By invoices = By.xpath("//a[@class='dropdown-item o_menu_entry_lvl_2 focus']");

    By invoiceRecord = By.xpath("//tbody/tr[1]/td[4]");

    By confirmInvoice = By.xpath("//span[normalize-space()='Confirm']");

    By registerPayment = By.xpath("//span[normalize-space()='Register Payment']");

    By createPayment = By.xpath("//span[normalize-space()='Create Payment']");

    By invoicesTenant = By.xpath("//a[normalize-space()='My Invoices']");

    By paidInvoices = By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/button[2]");

    public static String invoiceNumber;

    // Action methods


    public void openInvoicesAndAddInvoice() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        driver.findElement(invoicesMenuItem).click();

        driver.findElement(addInvoiceButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate and click the dropdown trigger
        By dropdownLocator = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]");
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();

        // Wait for dropdown options to appear
        By optionsListLocator = By.xpath("//ul[@class='p-dropdown-items' and @role='listbox']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsListLocator));

        // Select "Property 1" from the dropdown
        By property1Option = By.xpath("//li[@role='option']//span[@class='p-dropdown-item-label' and text()='Property 1']");
        WebElement property1Element = wait.until(ExpectedConditions.elementToBeClickable(property1Option));
        property1Element.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Locate and click the unit dropdown (the one currently showing "1")
        By unitDropdownLocator = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]");
        WebElement unitDropdown = wait.until(ExpectedConditions.elementToBeClickable(unitDropdownLocator));

        Thread.sleep(3000);
        js.executeScript("arguments[0].click();", unitDropdown);

        // Wait for dropdown options to appear
        By unitOptionsListLocator = By.xpath("//ul[@class='p-dropdown-items' and @role='listbox']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(unitOptionsListLocator));

        // Select the option with text "1" (note: there might be multiple elements with "1")
        // This XPath finds the exact match for the text "1" (not containing "1")
        By unit1Option = By.xpath("//li[@role='option']//span[@class='p-dropdown-item-label' and normalize-space()='1']");

        // Wait for the specific option to be clickable
        WebElement unit1Element = wait.until(ExpectedConditions.elementToBeClickable(unit1Option));

        // Click using JavaScript to avoid any interception issues
        js.executeScript("arguments[0].click();", unit1Element);

        Thread.sleep(3000);

        // Locate and click the tenant dropdown (the one showing "- Select Tenant -")
        By tenantDropdownLocator = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[1]/div[3]/div[2]/div[1]");
        WebElement tenantDropdown = wait.until(ExpectedConditions.elementToBeClickable(tenantDropdownLocator));


        js.executeScript("arguments[0].click();", tenantDropdown);

        // Wait for dropdown options to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@class='p-dropdown-items' and @role='listbox']")
        ));

        // Select the tenant with name "yarn.user.tenant"
        By tenantOptionLocator = By.xpath("//li[@role='option']//span[contains(@class, 'p-dropdown-item-label') and normalize-space()='yarn.user.tenant']");

        // Wait for the specific option to be clickable
        WebElement tenantOption = wait.until(ExpectedConditions.elementToBeClickable(tenantOptionLocator));

        // Click using JavaScript to avoid any interception issues
        js.executeScript("arguments[0].click();", tenantOption);

        Random random = new Random();

        Thread.sleep(3000);

        try {
            // 1. Click the services dropdown
            WebElement servicesDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[1]/div[5]/div[2]/div[1]")
            ));

            js.executeScript("arguments[0].click();", servicesDropdown);

            // 2. Wait for dropdown options to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'p-dropdown-panel')]")
            ));

            // 3. Get all service options
            List<WebElement> serviceOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[contains(@class, 'p-dropdown-panel')]//li[@role='option']")
            ));

            if (!serviceOptions.isEmpty()) {
                // 4. Select a random service option
                int randomIndex = random.nextInt(serviceOptions.size());
                WebElement selectedService = serviceOptions.get(randomIndex);
                String serviceName = selectedService.findElement(By.xpath(".//span")).getText();

                js.executeScript("arguments[0].scrollIntoView(true);", selectedService);
                js.executeScript("arguments[0].click();", selectedService);

                System.out.println("Selected service: " + serviceName);
            } else {
                throw new RuntimeException("No service options found in dropdown");
            }
        } catch (Exception e) {
            System.out.println("Error selecting service: " + e.getMessage());
            e.printStackTrace();
        }

        Thread.sleep(4000);

        // Create an instance of WebDriverWait
        WebDriverWait waitDate = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Click on the Preferred visit date input to open the date picker
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[1]/div[7]/div[2]/div[1]")));
        dateInput.click();

        Thread.sleep(4000);
        WebElement todayButton = driver.findElement(By.xpath("//button[@aria-label='Today']"));
        todayButton.click();

        WebElement dueDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[1]/div[8]/div[2]/div[1]/div[1]")));
        dueDateInput.click();

        Thread.sleep(4000);
        WebElement todayDueDateInput = driver.findElement(By.xpath("//button[@aria-label='Today']"));
        todayDueDateInput.click();

        driver.findElement(amount).sendKeys("1500");

        Thread.sleep(4000);


        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(submitInvoice).click();
    }

    /* محاولة إني أتعامل مع الأودو بشكل منفرد
    public void openInvoicesAndPostInvoice() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
        try {
            driver.findElement(mainMenu).click();

            // Locate and click the "Accounting" menu item by link text inside span
            WebElement accountingLink = driver.findElement(
                    By.xpath("//a[.//span[text()='Accounting']]")
            );

            accountingLink.click();

        } finally {
            System.out.println("Accounting module is opened. Weeeeeeeeeeeeeeeeeee!!!!!");
        }

        driver.findElement(customers).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        Thread.sleep(4000);
        // Wait for the "Invoices" link and click it
        WebElement invoicesLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space()='Invoices']")
                )
        );

        invoicesLink.click();


    }

     */

    public void openInvoiceDetailsAndPost() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));

        driver.findElement(invoiceRecord).click();

        Thread.sleep(2000);

        driver.findElement(confirmInvoice).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the span containing the invoice number to be visible
        By invoiceLocator = By.xpath("//span[@name='name' and contains(@class, 'o_field_char')]");

        WebElement invoiceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(invoiceLocator));

        // Get the text (invoice number)
        invoiceNumber = invoiceElement.getText().trim();

        if (!invoiceNumber.isEmpty()) {
            System.out.println("Invoice Number: " + invoiceNumber);
        } else {
            System.out.println("Invoice number not found or is empty.");
        }


    }

    public void checkInvoiceInTenant(String invoiceNumber) {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));

        driver.findElement(invoicesTenant).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Map<String, String> invoiceData = new HashMap<>();

        // Wait for the card to appear
        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[contains(@class, 'bg-[var(--c1)]')]"
        )));

        // Extract invoice number
        WebElement invoiceNumberElement = card.findElement(By.xpath(".//span[@class='me-2 text-xl']"));
        invoiceData.put("invoice_number", invoiceNumberElement.getText().trim());

        assert invoiceNumberElement.getText().trim().equals(invoiceNumber) : "Invoice number does not match! Expected: " + invoiceNumberElement.getText().trim() + ", Found: " + invoiceNumber;
        System.out.println("Invoice number matches successfully.");

        // Extract status (e.g., Posted)
        WebElement statusElement = card.findElement(By.xpath(".//span[contains(text(), 'Posted')]"));
        invoiceData.put("status", statusElement.getText().trim());

        // Extract due date
        WebElement dueDateElement = card.findElement(By.xpath(".//span[preceding-sibling::span[text()='Due date :']]"));
        invoiceData.put("due_date", dueDateElement.getText().trim());

        // Extract issuing date
        WebElement issuingDateElement = card.findElement(By.xpath(".//span[preceding-sibling::span[text()='Issuing date :']]"));
        invoiceData.put("issuing_date", issuingDateElement.getText().trim());

        // Extract due amount
        WebElement dueAmountElement = card.findElement(By.xpath(".//span[preceding-sibling::span[text()='Due amount :']]"));
        invoiceData.put("due_amount", dueAmountElement.getText().trim());

        // Extract paid amount
        WebElement paidAmountElement = card.findElement(By.xpath(".//span[preceding-sibling::span[text()='Paid amount :']]"));
        invoiceData.put("paid_amount", paidAmountElement.getText().trim());

        // Print extracted data
        for (Map.Entry<String, String> entry : invoiceData.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }



    }

    public void registerPaymentFromErp() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));

        Thread.sleep(2000);

        driver.findElement(registerPayment).click();

        driver.findElement(createPayment).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Thread.sleep(4000);

    }

    public void checkInvoiceInPaid(String invoiceNumber) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));

        Thread.sleep(2000);

        driver.findElement(paidInvoices).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Map<String, String> invoiceData = new HashMap<>();

        // Wait for at least one invoice card to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'bg-[var(--c1)]')]")));

        // Find all invoice cards
        List<WebElement> cards = driver.findElements(By.xpath("//div[contains(@class, 'bg-[var(--c1)]')]"));

        if (!cards.isEmpty()) {
            // Get the last card (newly added)
            WebElement lastCard = cards.get(cards.size() - 1);

            // Extract invoice number
            WebElement invoiceNumberElement = lastCard.findElement(By.xpath(".//span[@class='me-2 text-xl']"));
            invoiceData.put("invoice_number", invoiceNumberElement.getText().trim());

            assert invoiceNumberElement.getText().trim().equals(invoiceNumber) : "Invoice number does not match! Expected: " + invoiceNumberElement.getText().trim() + ", Found: " + invoiceNumber;
            System.out.println("Invoice number matches successfully.");

            // Extract status (e.g., Posted)
            WebElement statusElement = lastCard.findElement(By.xpath(".//span[contains(@class, 'capitalize') and normalize-space()='Posted']"));
            invoiceData.put("status", statusElement.getText().trim());

            // Extract due date
            WebElement dueDateElement = lastCard.findElement(By.xpath(".//span[preceding-sibling::span[text()='Due date :']]"));
            invoiceData.put("due_date", dueDateElement.getText().trim());

            // Extract issuing date
            WebElement issuingDateElement = lastCard.findElement(By.xpath(".//span[preceding-sibling::span[text()='Issuing date :']]"));
            invoiceData.put("issuing_date", issuingDateElement.getText().trim());

            // Extract due amount
            WebElement dueAmountElement = lastCard.findElement(By.xpath(".//span[preceding-sibling::span[text()='Due amount :']]"));
            invoiceData.put("due_amount", dueAmountElement.getText().trim());

            // Extract paid amount
            WebElement paidAmountElement = lastCard.findElement(By.xpath(".//span[preceding-sibling::span[text()='Paid amount :']]"));
            invoiceData.put("paid_amount", paidAmountElement.getText().trim());

            // Print extracted data
            System.out.println("=== Extracted Invoice Data ===");
            for (Map.Entry<String, String> entry : invoiceData.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } else {
            System.out.println("No invoice cards found.");
        }
    }
}


