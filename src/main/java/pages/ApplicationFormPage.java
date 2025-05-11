package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.time.Duration;

public class ApplicationFormPage {
    private WebDriver driver;

    @FindBy(xpath="//label[@for='MR']")
    private WebElement saluationMR;

    // Form fields
    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "fieldControl-input-dateOfBirth")
    private WebElement dob;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(id = "countryCode")
    private WebElement countryCode;

    @FindBy(name = "mobilePhoneNumber")
    private WebElement phoneNumberField;

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "postalCode")
    private WebElement postalCodeField;

    @FindBy(xpath = "//button[contains(text(),'Sauvegarder et Continuer')]")
    private WebElement saveAndContinueButton;

    @FindBy(xpath = "//span[contains(text(), 'Cochez cette case si votre')]")
    private WebElement birthNameSameChkBox;

    @FindBy(name = "placeOfBirth")
    private WebElement placeOfBirth;

    @FindBy(name = "departmentOfBirth")
    private WebElement birthDeptDropDown;

    @FindBy(name = "countryOfBirth")
    private WebElement countryOfBirthDropDown;

    @FindBy(name = "nationality")
    private WebElement nationalityDropDown;

    @FindBy(name = "country")
    private WebElement countryDropDown;

    @FindBy(name = "residentialAddressLine2")
    private WebElement residentialAddressLine2;

    @FindBy(name = "postcode")
    private WebElement postalcode;

    @FindBy(name = "cityTown")
    private WebElement cityTown;

    @FindBy(name = "personalResidentialStatus")
    private WebElement personalResidentialStatusDropDown;



    public ApplicationFormPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @Step("Fill application form page 1 with test data")
    public void fillApplicationForm_page1() throws InterruptedException {
        saluationMR.click();
        firstNameField.sendKeys(TestDataGenerator.getRandomFirstName());
        lastNameField.sendKeys(TestDataGenerator.getRandomLastName());
        dob.sendKeys(TestDataGenerator.getDOB(30,35));
        emailField.sendKeys(TestDataGenerator.getRandomEmail());
        WebElement countryCodeDropdown = driver.findElement(By.id("countryCode")); // Replace "countryCode" with the actual ID
        Select countrySelect = new Select(countryCodeDropdown);
        countrySelect.selectByValue("Inde");
        phoneNumberField.sendKeys(TestDataGenerator.getRandomPhoneNumber());
        clickSaveAndContinue();
    }

    @Step("Fill application form page 2 with test data")
    public void fillApplicationForm_page2() throws InterruptedException {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        birthNameSameChkBox.click();
        placeOfBirth.sendKeys(TestDataGenerator.getRandomCity());
        Select birthDeptSelect = new Select(birthDeptDropDown);
        birthDeptSelect.selectByValue("992");
        Select countOfBirthSelect = new Select(countryOfBirthDropDown);
        countOfBirthSelect.selectByValue("356");
        Select nationalitySelect = new Select(nationalityDropDown);
        nationalitySelect.selectByValue("356");
        Select countrySelect = new Select(countryDropDown);
        countrySelect.selectByValue("356");
        residentialAddressLine2.sendKeys(TestDataGenerator.getRandomAddress());
        postalcode.sendKeys(TestDataGenerator.getRandomPostalCode());
        cityTown.sendKeys(TestDataGenerator.getRandomCity());
        Select res_Status = new Select(personalResidentialStatusDropDown);
        res_Status.selectByValue("LIVING_WITH_PARENT");
        clickSaveAndContinue();
    }


    @Step("Click on 'Sauvegarder et Continuer' button")
    public void clickSaveAndContinue() throws InterruptedException {
        Thread.sleep(10000);
        By submit = By.xpath("//button[contains(text(),'Sauvegarder et Continuer')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        WebElement button = driver.findElement(submit);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
        //element.sendKeys(Keys.ENTER);
    }
}