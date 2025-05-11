package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AllCardsPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@data-qe-id=\"Column\"]//div[contains(text(), 'Carte Gold American')]")
    private WebElement goldCardLabel;

    @FindBy(xpath = "//a[@href=\"carte-de-paiement/gold-card-americanexpress/?linknav=fr-amex-cardshop-allcards-learn-GoldCardAmericanExpress<sup>®</sup>-fc\" and contains(text(), 'En savoir plus')]")
    private WebElement goldCardEnSaviorPlusBtn;

    public AllCardsPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @Step("Verify Gold Card is displayed.")
    public boolean verifyGoldCardLabel() {
        AmexHomePage home = new AmexHomePage(this.driver);
        home.acceptCookies();
        return goldCardLabel.isDisplayed();
    }

    @Step("Verify Gold Card is displayed.")
    public void clickGoldCardEnSaviorPlus() {
        goldCardEnSaviorPlusBtn.click();
    }
}