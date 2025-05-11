package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class GoldCardPage {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(), 'Demandez votre Carte')]")
    private WebElement demandezVotreCarteButton;

    @FindBy(xpath = "//h1[@data-qe-id='CardTitleText']")
    private WebElement goldAmexTitle;

    public GoldCardPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @Step("Click on 'Demandez votre carte' button")
    public void clickDemandezVotreCarte() {
        demandezVotreCarteButton.click();
    }

    @Step("Fetch on Gold Amex Webpage Title")
    public String getGoldAmexTitle() {
        AmexHomePage home = new AmexHomePage(this.driver);
        home.acceptCookies();
        return goldAmexTitle.getText();
    }
}