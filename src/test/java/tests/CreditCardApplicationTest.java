package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmexHomePage;
import pages.AllCardsPage;
import pages.ApplicationFormPage;
import pages.GoldCardPage;

import utils.DriverManager;


@Feature("American Express FR Credit Card Application")
public class CreditCardApplicationTest extends BaseTest {
    WebDriver driver =  DriverManager.getDriver();
    @Test
    @Story("User applies for Gold Card")
    @Description("Test the complete flow of applying for an American Express Gold Card in France")
    public void testGoldCardApplicationFlow() throws InterruptedException {
        AmexHomePage homePage = new AmexHomePage(driver);
        AllCardsPage allCardsPage = new AllCardsPage(driver);
        GoldCardPage goldCardPage = new GoldCardPage(driver);
        ApplicationFormPage applicationFormPage = new ApplicationFormPage(driver);
        homePage.clickCartesAmericanExpress();
        Assert.assertTrue(allCardsPage.verifyGoldCardLabel(), "Looks like Gold Card is not displayed");
        allCardsPage.clickGoldCardEnSaviorPlus();
        Assert.assertTrue(goldCardPage.getGoldAmexTitle().contains("Carte Gold American Express"));
        goldCardPage.clickDemandezVotreCarte();
        applicationFormPage.fillApplicationForm_page1();
        applicationFormPage.fillApplicationForm_page2();
    }

}