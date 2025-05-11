package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmexHomePage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='product-nav-item-content ']//p[contains(text(), 'Cartes Particuliers')]")
    private WebElement cartesAmericanExpressLink;

    @FindBy(xpath = "//button[contains(text(),'Tout Accepter')]")
    private WebElement acceptCookies;

    public AmexHomePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void clickCartesAmericanExpress() {
        acceptCookies();
        cartesAmericanExpressLink.click();
    }

    public void acceptCookies(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Tout Accepter')]"))
            );
        if (element.isDisplayed()) {
            element.click();
        }}
        catch (Exception ignored){}
    }
}